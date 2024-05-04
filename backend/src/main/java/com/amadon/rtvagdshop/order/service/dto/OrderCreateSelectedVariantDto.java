package com.amadon.rtvagdshop.order.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateSelectedVariantDto
{
    private Long categoryId;
    private Long variantId;
}
