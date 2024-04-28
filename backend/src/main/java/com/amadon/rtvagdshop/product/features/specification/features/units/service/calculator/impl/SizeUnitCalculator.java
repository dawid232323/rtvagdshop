package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl;

import com.amadon.rtvagdshop.product.features.specification.features.units.entity.SizeUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@Component
public class SizeUnitCalculator implements UnitCalculator< SizeUnitsEnum >
{
    @Override
    public SizeUnitsEnum calculateDisplayUnit( final Double currentValue )
    {
        return Arrays.stream( SizeUnitsEnum.values() )
                .filter( unit -> ( currentValue / unit.getMultiplier() ) > 1 )
                .max( Comparator.comparing( SizeUnitsEnum::getMultiplier ) )
                .orElse( getDefaultUnit() );
    }

    @Override
    public String calculateDefault( final SizeUnitsEnum currentUnit, final Double currentValue )
    {
        final Double multipliedValue = currentUnit.getMultiplier() * currentValue;
        return String.valueOf( multipliedValue.intValue() );
    }

    @Override
    public SizeUnitsEnum getDefaultUnit()
    {
        return SizeUnitsEnum.MILLIMITER;
    }
}
