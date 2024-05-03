package com.amadon.rtvagdshop.product.service.dto;

import com.amadon.rtvagdshop.product.features.category.service.dto.ProductCategoryDto;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryDto;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.amadon.rtvagdshop.product.entity.Product}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto implements Serializable
{
    private Long id;
    private String displayName;
    private Long basePrice;
    private List< ProductCategoryDto > categories;
    private List< ProductSpecificationCategoryDto > specificationCategories;
    private List< ProductVariantCategoryDto > variantCategories;
}