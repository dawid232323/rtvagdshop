package com.amadon.rtvagdshop.order.service.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.amadon.rtvagdshop.order.entity.ProductInfoSelectedVariant}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductInfoSelectedVariantDto implements Serializable
{
    private Long id;
    private Long variantCategoryId;
    private String variantCategoryName;
    private Long variantId;
    private String variantCode;
    private String variantValue;
    private Double additionalPrice;
}