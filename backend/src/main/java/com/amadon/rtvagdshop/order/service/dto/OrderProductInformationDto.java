package com.amadon.rtvagdshop.order.service.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.amadon.rtvagdshop.order.entity.OrderProductInformation}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderProductInformationDto implements Serializable
{
    private Long id;
    private Long productId;
    private String productName;
    private Double basePrice;
    private String orderCode;
    private List< ProductInfoSelectedVariantDto > productInfoSelectedVariants = new ArrayList<>();
}