package com.amadon.rtvagdshop.product.features.specification.service.dto;

import com.amadon.rtvagdshop.shared.service.validator.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.Common.fieldRequired;
import static com.amadon.rtvagdshop.shared.service.validator.ValidationMessages.List.emptyNotNull;

/**
 * DTO for {@link com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecificationCategory}
 */
@Getter
@Setter
public class ProductSpecificationCategoryCreateDto implements Serializable
{
    @NotNull( message = fieldRequired )
    @Size( min = 2, max = 500 )
    private String name;

    @NotNull( message = emptyNotNull )
    @Size( min = 1, max = 10 )
    private List< ProductSpecificationCreateDto > productSpecifications;
}