package com.amadon.rtvagdshop.product.features.specification.service.validator;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCreateDto;

public interface ProductSpecificationValidatorRule
{
    void validate( ProductSpecificationCreateDto aCreateDto, Product aProduct );
}
