package com.amadon.rtvagdshop.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDto
{
    private Long id;
    private String displayName;
    private String code;
    private Long topicCategoryId;
    private String topicCategoryName;
    private Long topicId;
    private String topicName;
}
