package com.amadon.rtvagdshop.product.features.specification.service.dto;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecificationCategoryDto
{
    private Long id;
    private String name;
    private List< ProductSpecificationDto > specifications;
}
