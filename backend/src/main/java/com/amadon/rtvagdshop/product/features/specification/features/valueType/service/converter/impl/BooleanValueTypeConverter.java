package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import org.springframework.stereotype.Component;

@Component
public class BooleanValueTypeConverter implements SpecificationValueTypeConvertStrategy< Boolean >
{
    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< Boolean > aSpecificationDto )
    {
        final Boolean value = Boolean.parseBoolean( productSpecification.getValue() );
        aSpecificationDto.setSpecificationValue( value );
        aSpecificationDto.setUnit( null );
    }

    @Override
    public void convertFromDto( final ProductSpecificationDto< Boolean > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        productSpecification.setValue( aSpecificationDto.getSpecificationValue().toString() );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.BOOLEAN );
    }
}
