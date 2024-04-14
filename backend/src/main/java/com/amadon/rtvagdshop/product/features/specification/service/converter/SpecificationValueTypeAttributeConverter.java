package com.amadon.rtvagdshop.product.features.specification.service.converter;

import com.amadon.rtvagdshop.product.features.specification.entity.SpecificationValueType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter( autoApply = true )
public class SpecificationValueTypeAttributeConverter implements AttributeConverter< SpecificationValueType, String >
{
    @Override
    public String convertToDatabaseColumn( final SpecificationValueType aSpecificationValueType )
    {
        if ( Objects.isNull( aSpecificationValueType ) )
        {
            throw new IllegalArgumentException( "SpecificationValueType cannot be null" );
        }
        return aSpecificationValueType.getTypeName();
    }

    @Override
    public SpecificationValueType convertToEntityAttribute( final String aS )
    {
        return SpecificationValueType.valueOf( aS );
    }
}
