package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.units.entity.WeightUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeightSpecificationValueTypeConverter implements SpecificationValueTypeConvertStrategy< Double >
{
    private final UnitCalculator< WeightUnitsEnum > unitCalculator;

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< Double > aSpecificationDto )
    {
        final Double currentValue = Double.parseDouble( productSpecification.getValue() );
        final WeightUnitsEnum displayUnit = unitCalculator.calculateDisplayUnit( currentValue );
        aSpecificationDto.setUnit( displayUnit.getShortcut() );
        aSpecificationDto.setSpecificationValue( currentValue / displayUnit.getMultiplier() );
    }

    @Override
    public void convertFromDto( final ProductSpecificationDto< Double > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        final WeightUnitsEnum unitsEnum = WeightUnitsEnum.fromShortcut( aSpecificationDto.getUnit() );
        final String defaultValue = unitCalculator.calculateDefault( unitsEnum,
                aSpecificationDto.getSpecificationValue() );
        productSpecification.setValue( defaultValue );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.WEIGHT );
    }
}
