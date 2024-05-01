package com.amadon.rtvagdshop.product.features.variant.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantCategory}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantCategoryDto implements Serializable
{
    private Long id;
    private String categoryName;
    private Boolean isAvailable;
    private List< ProductVariantDetailDto > variantDetails;
}