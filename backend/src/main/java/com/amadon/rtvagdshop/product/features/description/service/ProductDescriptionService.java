package com.amadon.rtvagdshop.product.features.description.service;

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
}
