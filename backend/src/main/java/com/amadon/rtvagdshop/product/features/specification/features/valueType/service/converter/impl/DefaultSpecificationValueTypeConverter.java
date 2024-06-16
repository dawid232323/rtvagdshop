package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.ProductSpecificationIf;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import org.springframework.stereotype.Component;

@Component
public class DefaultSpecificationValueTypeConverter implements SpecificationValueTypeConvertStrategy< String >
{

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< String > aSpecificationDto )
    {
        aSpecificationDto.setSpecificationValue( productSpecification.getValue() );
        aSpecificationDto.setUnit( null );
    }

    @Override
    public void convertFromDto( final ProductSpecificationIf< String > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        productSpecification.setValue( aSpecificationDto.getSpecificationValue() );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.STRING )
                || specificationValueType.equals( SpecificationValueType.OTHER );
    }
}
