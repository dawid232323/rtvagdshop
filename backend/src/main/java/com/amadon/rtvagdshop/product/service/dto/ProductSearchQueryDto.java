package com.amadon.rtvagdshop.product.service.dto;

import com.amadon.rtvagdshop.shared.service.validator.ValidationMessages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSearchQueryDto
{
    @NotNull
    @Min( value = 1, message = ValidationMessages.PrimaryKey.validValue )
    private Long categoryId;
}
