package com.amadon.rtvagdshop.order.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto
{
    private Long productId;
    private List<OrderCreateSelectedVariantDto> selectedVariants;
}
