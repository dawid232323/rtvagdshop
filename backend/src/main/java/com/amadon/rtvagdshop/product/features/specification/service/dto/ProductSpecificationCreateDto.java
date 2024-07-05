package com.amadon.rtvagdshop.product.features.specification.service.dto;

import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.service.ProductSpecificationIf;
import com.amadon.rtvagdshop.product.features.specification.service.converter.SpecificationValueTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class ProductSpecificationCreateDto implements ProductSpecificationIf< Object >
{
    @NotNull
    @Size( min = 2, max = 50 )
    private String code;

    @NotNull
    @Size( min = 2, max = 500 )
    private String displayName;

    @NotNull
    @JsonDeserialize( using = SpecificationValueTypeDeserializer.class )
    private SpecificationValueType valueType;

    private String unit;

    @NotNull
    private Object value;

    private boolean isDescriptionAvailable;

    @NotNull
    private List< String > onlyAvailableInVariants;

    @Override
    public Object getSpecificationValue()
    {
        return this.value;
    }
}
