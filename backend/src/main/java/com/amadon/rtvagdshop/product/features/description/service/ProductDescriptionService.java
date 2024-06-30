package com.amadon.rtvagdshop.product.features.description.service;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.description.entity.ProductDescription;
import com.amadon.rtvagdshop.product.features.description.service.mapper.ProductDescriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDescriptionService
{
    private final ProductDescriptionPersistenceService persistenceService;
    private final ProductDescriptionMapper productDescriptionMapper;

    public String getDescriptionResponseByProductId( final Long aProductId )
    {
        final ProductDescription productDescription = persistenceService.getProductDescriptionByProductId( aProductId );
        return productDescriptionMapper.mapToHtml( productDescription );
    }

    public ProductDescription createDescriptionForProduct( final String aDescription, final Product aProduct )
    {
        final ProductDescription description = ProductDescription.builder()
                .description( aDescription )
                .product( aProduct )
                .build();
        persistenceService.saveProductDescription( description );
        return description;
    }
}
