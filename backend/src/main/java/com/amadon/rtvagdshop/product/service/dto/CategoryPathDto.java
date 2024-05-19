package com.amadon.rtvagdshop.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPathDto
{
    private Long categoryId;
    private String categoryName;
    private String urlFragment;
}
