package com.amadon.rtvagdshop.order.service.creator.impl;

import com.amadon.rtvagdshop.order.entity.ProductInfoSelectedVariant;
import com.amadon.rtvagdshop.order.service.creator.OrderCodeCreateStrategy;
import com.amadon.rtvagdshop.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultOrderCodeCreateStrategy implements OrderCodeCreateStrategy
{

    @Override
    public String getOrderCode( final Product aProduct, final List< ProductInfoSelectedVariant > aSelectedVariants )
    {
        final String decodedString = getDecodedString( aProduct, aSelectedVariants );
        return Base64.getEncoder()
                .encodeToString( decodedString.getBytes( StandardCharsets.UTF_8 ) );
    }

    @Override
    public boolean isApplicable( final Product aProduct )
    {
        return true;
    }

    protected String getDecodedString( final Product aProduct,
                                       final List< ProductInfoSelectedVariant > aSelectedVariants )
    {
        String base = String.format( "product:%s_", aProduct.getDisplayName() );
        String selectedVariantsString = aSelectedVariants.stream()
                .map( variant -> String.format( "%s:%s", variant.getVariantCategoryName(), variant.getVariantCode() ) )
                .collect( Collectors.joining( "_" ) );
        return base.concat( selectedVariantsString );
    }
}
