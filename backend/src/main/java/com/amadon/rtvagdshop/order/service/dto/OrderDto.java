package com.amadon.rtvagdshop.order.service.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.amadon.rtvagdshop.order.entity.Order}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderDto implements Serializable
{
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private OrderBuyerInformationDto orderBuyerInformation;
    private OrderProductInformationDto orderProductInformation;
}