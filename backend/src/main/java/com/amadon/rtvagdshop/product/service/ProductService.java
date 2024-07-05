package com.amadon.rtvagdshop.product.service;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.description.service.ProductDescriptionService;
import com.amadon.rtvagdshop.product.features.specification.service.ProductSpecificationService;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantCategory;
import com.amadon.rtvagdshop.product.features.variant.service.ProductVariantService;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantCategoryCreateDto;
import com.amadon.rtvagdshop.product.service.creator.ProductCreatorStrategy;
import com.amadon.rtvagdshop.product.service.dto.*;
import com.amadon.rtvagdshop.product.service.exception.LackOfCreateStrategyException;
import com.amadon.rtvagdshop.product.service.mapper.ProductMapper;
import com.amadon.rtvagdshop.product.service.util.CategoryPathBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductMapper productMapper;
    private final ProductPersistenceService persistenceService;
    private final CategoryPathBuilder categoryPathBuilder;

    private final ProductSpecificationService specificationService;
    private final ProductVariantService variantService;
    private final ProductDescriptionService descriptionService;

    private final List< ProductCreatorStrategy > creatorStrategies;

    public ProductDto getProductDtoById( final Long aProductId )
    {
        return productMapper.mapToDto( persistenceService.getProduct( aProductId ) );
    }

    public Product getProduct( final Long aProductId )
    {
        return persistenceService.getProduct( aProductId );
    }

    @Transactional
    public ProductDto initProduct( final InitProductDto aInitProductDto )
    {
        final ProductCreatorStrategy strategy = resolveCreateStrategy();
        final Product createdProduct = strategy.createProduct( aInitProductDto );
        return productMapper.mapToDto( persistenceService.saveProduct( createdProduct ) );
    }

    @Transactional
    public ProductDto createProductSpecifications( final List< ProductSpecificationCategoryCreateDto > aCreateDtos,
                                                   final Long aProductId )
    {
        final Product product = getProduct( aProductId );
        specificationService.createSpecificationForProduct( aCreateDtos, product );

        persistenceService.saveProduct( product );

        return productMapper.mapToDto( product );
    }

    @Transactional
    public ProductDto createProductVariants( final List< ProductVariantCategoryCreateDto > aCategoryCreateDtos,
                                             final Long aProductId )
    {
        final Product product = getProduct( aProductId );
        final List< ProductVariantCategory > variantCategories =
                variantService.createVariantsForProduct( aCategoryCreateDtos );

        product.setVariantCategories( variantCategories );
        variantCategories.forEach( category -> category.setProduct( product ) );
        persistenceService.saveProduct( product );

        return productMapper.mapToDto( product );
    }

    @Transactional
    public String createProductDescription( final String aProductDescription, final Long aProductId )
    {
        final Product product = getProduct( aProductId );
        descriptionService.createDescriptionForProduct( aProductDescription, product );
        return aProductDescription;
    }

    public Page< ProductSearchResultDto > searchForProducts( final ProductSearchQueryDto aSearchQueryDto,
                                                             final Pageable aPageable )
    {
        final Page< Product > results = persistenceService.searchForProducts( aSearchQueryDto, aPageable );
        return getSearchResults( aSearchQueryDto, results );
    }

    private Page< ProductSearchResultDto > getSearchResults( final ProductSearchQueryDto aSearchQueryDto,
                                                             final Page< Product > results )
    {
        final Long aCategoryId = aSearchQueryDto.getCategoryId();
        if ( Objects.isNull( aCategoryId ) )
        {
            return results.map( productMapper::mapToSearchResult );
        }
        return results.map( result ->
        {
            final ProductSearchResultDto mappedResult = productMapper.mapToSearchResult( result );
            final List< CategoryPathDto > categoryPaths =
                    categoryPathBuilder.buildCategoryPath( result.getCategories(), aCategoryId );
            mappedResult.setCategoryPaths( categoryPaths );
            return mappedResult;
        } );
    }

    private ProductCreatorStrategy resolveCreateStrategy()
    {
        return creatorStrategies.stream()
                .filter( ProductCreatorStrategy::isApplicable )
                .findFirst()
                .orElseThrow( LackOfCreateStrategyException::new );
    }
}
