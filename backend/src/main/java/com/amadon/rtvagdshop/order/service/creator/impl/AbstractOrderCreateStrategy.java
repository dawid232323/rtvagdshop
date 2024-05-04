package com.amadon.rtvagdshop.order.service.creator.impl;

import com.amadon.rtvagdshop.address.entity.Address;
import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.entity.OrderBuyerInformation;
import com.amadon.rtvagdshop.order.entity.OrderProductInformation;
import com.amadon.rtvagdshop.order.entity.ProductInfoSelectedVariant;
import com.amadon.rtvagdshop.order.service.creator.OrderCodeCreateStrategy;
import com.amadon.rtvagdshop.order.service.creator.OrderCreateStrategy;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateSelectedVariantDto;
import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantCategory;
import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantDetail;
import com.amadon.rtvagdshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractOrderCreateStrategy implements OrderCreateStrategy
{

    private final ProductService productService;
    private final List< OrderCodeCreateStrategy > codeCreateStrategies;

    @Override
    public Order create( final OrderCreateDto aCreateDto )
    {
        final Order order = new Order();
        order.setOrderBuyerInformation( fillBuyerInformation() );
        order.setOrderProductInformation( fillProductInfo( aCreateDto ) );
//        order.setCreatedAt( Instant.now() );
//        order.setUpdatedAt( Instant.now() );
        return order;
    }

    protected OrderBuyerInformation fillBuyerInformation()
    {
        final OrderBuyerInformation orderBuyerInformation = new OrderBuyerInformation();
        orderBuyerInformation.setShippingAddress( fillAddress() );
        return orderBuyerInformation;
    }

    protected Address fillAddress()
    {
        return null;
    }

    protected OrderProductInformation fillProductInfo( final OrderCreateDto aCreateDto )
    {
        final Product product = productService.getProduct( aCreateDto.getProductId() );
        final List< ProductInfoSelectedVariant > selectedVariants = fillSelectedVariants( product, aCreateDto.getSelectedVariants() );
        final String orderCode = getOrderCode( product, selectedVariants );
        return OrderProductInformation.builder()
                .productId( aCreateDto.getProductId() )
                .productName( product.getDisplayName() )
                .basePrice( Double.valueOf( product.getBasePrice() ) )
                .productInfoSelectedVariants( selectedVariants )
                .orderCode( orderCode )
                .build();
    }

    protected List< ProductInfoSelectedVariant > fillSelectedVariants( final Product aProduct,
                                                               final List< OrderCreateSelectedVariantDto > aProductInfoSelectedVariants )
    {
        final Map< Long, Long > selectedVariantsMap = getSelectedVariantsMap( aProductInfoSelectedVariants );
        return aProduct.getVariantCategories()
                .stream()
                .filter( variantCategory -> selectedVariantsMap.containsKey( variantCategory.getId() ) )
                .map( variantCategory -> getSelectedVariant( variantCategory, selectedVariantsMap ) )
                .toList();
    }

    protected String getOrderCode( final Product aProduct,
                                   final List< ProductInfoSelectedVariant > aProductInfoSelectedVariants )
    {
        final OrderCodeCreateStrategy strategy = codeCreateStrategies.stream()
                .filter( aOrderCodeCreateStrategy -> aOrderCodeCreateStrategy.isApplicable( aProduct ) )
                .findAny()
                .orElseThrow();
        return strategy.getOrderCode( aProduct, aProductInfoSelectedVariants );
    }

    private Map< Long, Long > getSelectedVariantsMap( final List< OrderCreateSelectedVariantDto > aProductInfoSelectedVariants )
    {
        return aProductInfoSelectedVariants.stream()
                .collect( Collectors.toMap( OrderCreateSelectedVariantDto::getCategoryId,
                        OrderCreateSelectedVariantDto::getVariantId ) );
    }

    private ProductInfoSelectedVariant getSelectedVariant( final ProductVariantCategory aVariantCategory,
                                                           final Map< Long, Long > aSelectedVariantsMap )
    {
        final ProductVariantDetail variantDetail = aVariantCategory.getVariantDetails()
                .stream()
                .filter( details -> details.getId()
                        .equals( aSelectedVariantsMap.get( aVariantCategory.getId() ) ) )
                .findFirst()
                .orElseThrow();
        return ProductInfoSelectedVariant.builder()
                .variantCategoryId( aVariantCategory.getId() )
                .variantCategoryName( aVariantCategory.getCategoryName() )
                .variantId( variantDetail.getId() )
                .variantCode( variantDetail.getCode() )
                .variantValue( variantDetail.getValue() )
                .additionalPrice( variantDetail.getAdditionalPrice() )
                .build();
    }
}
