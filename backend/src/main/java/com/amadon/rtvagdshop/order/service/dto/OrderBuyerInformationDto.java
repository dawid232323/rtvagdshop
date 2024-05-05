package com.amadon.rtvagdshop.order.service.dto;

import com.amadon.rtvagdshop.address.service.dto.AddressDto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Common.fieldMaxLength;
import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Common.fieldRequired;
import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Email.emailRequired;
import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Email.invalidEmail;
import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Name.firstNameMaxLength;
import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Name.lastNameMaxLength;

/**
 * DTO for {@link com.amadon.rtvagdshop.order.entity.OrderBuyerInformation}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderBuyerInformationDto implements Serializable
{
    private Long id;

    @NotNull( message = fieldRequired )
    private AddressDto shippingAddress;

    private AddressDto correspondenceAddress;

    @Size( max = 400, min = 2, message = firstNameMaxLength )
    private String firstName;

    @Size( max = 400, min = 2, message = lastNameMaxLength )
    private String lastName;

    @NotNull( message = emailRequired )
    @NotEmpty( message = emailRequired )
    @Email( message = invalidEmail )
    private String email;

    @Size( max = 100, message = fieldMaxLength )
    private String phoneNumber;

    private Boolean isInvoiceRequired;

    @Size( max = 50, message = fieldMaxLength )
    private String nip;

    @Size( max = 50, message = fieldMaxLength )
    private String regon;

    @Size( max = 500, message = fieldMaxLength )
    private String companyName;
}