package com.amadon.rtvagdshop.product.features.specification.service.dto;

import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.service.ProductSpecificationIf;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecificationCreateDto implements ProductSpecificationIf< String >
{
    @NotNull
    @Size( min = 2, max = 50 )
    private String code;

    @NotNull
    @Size( min = 2, max = 500 )
    private String displayName;

    @NotNull
    private SpecificationValueType valueType;

    @NotNull
    private String unit;

    @NotNull
    private String value;

    private boolean isDescriptionAvailable;

    @NotNull
    private List< String > onlyAvailableInVariants;

    @Override
    public String getSpecificationValue()
    {
        return this.value;
    }
}
