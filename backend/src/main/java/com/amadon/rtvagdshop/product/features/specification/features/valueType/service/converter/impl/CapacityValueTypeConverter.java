package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.units.entity.CapacityUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CapacityValueTypeConverter implements SpecificationValueTypeConvertStrategy< Double >
{
    private final UnitCalculator< CapacityUnitsEnum > unitCalculator;

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< Double > aSpecificationDto )
    {
        final Double currentValue = Double.parseDouble( productSpecification.getValue() );
        final CapacityUnitsEnum displayUnit = unitCalculator.calculateDisplayUnit( currentValue );
        final Double convertedValue = unitCalculator.convert( currentValue, displayUnit,
                unitCalculator.getDefaultUnit() );

        aSpecificationDto.setUnit( displayUnit.getShortcut() );
        aSpecificationDto.setSpecificationValue( convertedValue );
    }

    @Override
    public void convertFromDto( final ProductSpecificationDto< Double > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {

    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.CAPACITY );
    }
}
