package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl;

import com.amadon.rtvagdshop.product.features.specification.features.units.entity.CapacityUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@Component
public class CapacityUnitCalculator implements UnitCalculator< CapacityUnitsEnum >
{

    @Override
    public CapacityUnitsEnum calculateDisplayUnit( final Double currentValue )
    {
        return Arrays.stream( CapacityUnitsEnum.values() )
                .filter( unit -> ( currentValue / unit.getMultiplier() ) > 1 )
                .max( Comparator.comparing( CapacityUnitsEnum::getMultiplier ) )
                .orElse( getDefaultUnit() );
    }

    @Override
    public CapacityUnitsEnum getDefaultUnit()
    {
        return CapacityUnitsEnum.MILLI_LITERS;
    }
}
