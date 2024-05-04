package com.amadon.rtvagdshop.address.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.amadon.rtvagdshop.address.entity.Address}
 */
@Getter
@Setter
public class AddressDto implements Serializable
{
    private Long id;
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
}