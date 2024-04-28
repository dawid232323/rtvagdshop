package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl;

import com.amadon.rtvagdshop.product.features.specification.features.units.entity.TimeUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@Component
public class TimeUnitCalculator implements UnitCalculator< TimeUnitsEnum >
{
    @Override
    public TimeUnitsEnum calculateDisplayUnit( final Double currentValue )
    {
        return Arrays.stream( TimeUnitsEnum.values() )
                .filter( unit -> ( currentValue / unit.getMultiplier() ) > 1 )
                .max( Comparator.comparing( TimeUnitsEnum::getMultiplier ) )
                .orElse( getDefaultUnit() );
    }

    @Override
    public TimeUnitsEnum getDefaultUnit()
    {
        return TimeUnitsEnum.SECONDS;
    }
}
