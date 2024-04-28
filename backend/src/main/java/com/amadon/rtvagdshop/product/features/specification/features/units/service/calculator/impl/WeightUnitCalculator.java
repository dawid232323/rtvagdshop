package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl;

import com.amadon.rtvagdshop.product.features.specification.features.units.entity.WeightUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@Component
public class WeightUnitCalculator implements UnitCalculator< WeightUnitsEnum >
{
    @Override
    public WeightUnitsEnum calculateDisplayUnit( final Double currentValue )
    {
        return Arrays.stream( WeightUnitsEnum.values() )
                .filter( unit -> ( currentValue / unit.getMultiplier() ) > 1 )
                .max( Comparator.comparing( WeightUnitsEnum::getMultiplier ) )
                .orElse( getDefaultUnit() );
    }

    @Override
    public String calculateDefault( final WeightUnitsEnum currentUnit, final Double currentValue )
    {
        final Double multipliedValue = currentUnit.getMultiplier() * currentValue;
        return String.valueOf( multipliedValue.intValue() );
    }

    @Override
    public WeightUnitsEnum getDefaultUnit()
    {
        return WeightUnitsEnum.MILLIGRAM;
    }
}
