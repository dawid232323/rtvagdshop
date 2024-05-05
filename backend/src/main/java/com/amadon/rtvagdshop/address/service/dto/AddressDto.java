package com.amadon.rtvagdshop.address.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Common.fieldRequired;

/**
 * DTO for {@link com.amadon.rtvagdshop.address.entity.Address}
 */
@Getter
@Setter
public class AddressDto implements Serializable
{
    private Long id;
    @NotNull( message = fieldRequired )
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
}