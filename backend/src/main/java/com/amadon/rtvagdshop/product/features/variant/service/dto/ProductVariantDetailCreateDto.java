package com.amadon.rtvagdshop.product.features.variant.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantDetail}
 */
@Getter
@Setter
public class ProductVariantDetailCreateDto implements Serializable
{
    private Boolean isDefault;

    @NotNull
    @NotBlank
    @Size( min = 50, max = 50 )
    private String code;

    private String value;

    private Double additionalPrice;
}