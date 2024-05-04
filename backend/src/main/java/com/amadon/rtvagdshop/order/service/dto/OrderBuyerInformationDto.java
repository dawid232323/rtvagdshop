package com.amadon.rtvagdshop.order.service.dto;

import com.amadon.rtvagdshop.address.service.dto.AddressDto;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.amadon.rtvagdshop.order.entity.OrderBuyerInformation}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderBuyerInformationDto implements Serializable
{
    private Long id;
    private AddressDto shippingAddress;
    private AddressDto correspondenceAddress;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean isInvoiceRequired;
    private String nip;
    private String regon;
    private String companyName;
}