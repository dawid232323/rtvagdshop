package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.units.entity.TimeUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.ProductSpecificationIf;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeSpecificationValueTypeConverter implements SpecificationValueTypeConvertStrategy< Double >
{
    private final UnitCalculator< TimeUnitsEnum > timeCalculator;

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< Double > aSpecificationDto )
    {
        final Double specificationTimeValue = Double.parseDouble( productSpecification.getValue() );
        final TimeUnitsEnum displayUnit = timeCalculator.calculateDisplayUnit( specificationTimeValue );
        final Double convertedValue = timeCalculator.convert( specificationTimeValue, displayUnit,
                timeCalculator.getDefaultUnit() );

        aSpecificationDto.setUnit( displayUnit.getShortcut() );
        aSpecificationDto.setSpecificationValue( convertedValue );
    }

    @Override
    public void convertFromDto( final ProductSpecificationIf< Double > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        final TimeUnitsEnum currentUnit = TimeUnitsEnum.fromShortcut( aSpecificationDto.getUnit() );
        final String value = timeCalculator.calculateDefault( currentUnit, aSpecificationDto.getSpecificationValue() );
        productSpecification.setValue( value );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.TIME );
    }
}
