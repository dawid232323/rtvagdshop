package com.amadon.rtvagdshop.product.features.specification.features.units.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CapacityUnitsEnum implements UnitValue
{
    MILLI_LITERS( "ml", 1d ),
    LITERS( "l", 1000d ),
    CUBIC_METERS( "m3", 1000000d );

    private final String shortcut;
    private final Double multiplier;

    public static CapacityUnitsEnum fromShortcut( final String aShortcut )
    {
        return Arrays.stream( values() )
                .filter( value -> value.getShortcut().equals( aShortcut ) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( "Unknown shortcut for weight enum: " + aShortcut ) );
    }
}
