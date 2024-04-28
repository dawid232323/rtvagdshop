package com.amadon.rtvagdshop.product.features.specification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecificationDto<T>
{
    private String code;
    private String displayName;
    private String type;
    private Boolean isDescriptionAvailable;
    private List<String> availableInVariants;
    private String unit;
    private T specificationValue;
    private Long specificationCategoryId;
    private String specificationCategoryName;
}
