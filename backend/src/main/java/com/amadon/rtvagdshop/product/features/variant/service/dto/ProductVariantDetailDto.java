package com.amadon.rtvagdshop.product.features.variant.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantDetail}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDetailDto implements Serializable
{
    private Long id;
    private Boolean isAvailable;
    private Boolean isDefault;
    private String code;
    private String value;
    private Double additionalPrice;
}