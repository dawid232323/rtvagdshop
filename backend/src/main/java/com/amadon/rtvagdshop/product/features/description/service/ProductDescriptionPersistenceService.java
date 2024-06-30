package com.amadon.rtvagdshop.product.features.description.service;

import com.amadon.rtvagdshop.product.features.description.entity.ProductDescription;
import com.amadon.rtvagdshop.product.features.description.service.exception.ProductDescriptionNotFoundException;
import com.amadon.rtvagdshop.product.features.description.service.repository.ProductDescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class ProductDescriptionPersistenceService
{
    private final ProductDescriptionRepository productDescriptionRepository;

    public ProductDescription getProductDescription( final Long aProductDescriptionId )
    {
        final Optional< ProductDescription > productDescription =
                productDescriptionRepository.findById( aProductDescriptionId );
        if ( productDescription.isEmpty() )
        {
            throw new ProductDescriptionNotFoundException( "Could not find product description with id " + aProductDescriptionId.toString() );
        }
        return productDescription.get();
    }

    public ProductDescription getProductDescriptionByProductId( final Long aProductId )
    {
        final Optional< ProductDescription > productDescription =
                productDescriptionRepository.findByProductId( aProductId );
        if ( productDescription.isEmpty() )
        {
            throw new ProductDescriptionNotFoundException( "Could not find product description with product id " + aProductId.toString() );
        }
        return productDescription.get();
    }

    public void saveProductDescription( final ProductDescription aProductDescription )
    {
        productDescriptionRepository.save( aProductDescription );
    }
}
