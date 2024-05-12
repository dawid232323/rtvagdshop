package com.amadon.rtvagdshop.product.features.description.service.mapper;

import com.amadon.rtvagdshop.product.features.description.entity.ProductDescription;

public interface ProductDescriptionMapper
{
    String mapToHtml( ProductDescription aProductDescription );
}
