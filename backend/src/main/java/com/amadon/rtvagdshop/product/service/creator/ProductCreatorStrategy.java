package com.amadon.rtvagdshop.product.service.creator;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.service.dto.InitProductDto;

public interface ProductCreatorStrategy
{
    Product createProduct( InitProductDto aInitProductDto );

    boolean isApplicable();
}
