package com.amadon.rtvagdshop.product.features.specification.features.units.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TimeUnitsEnum implements UnitValue
{
    SECONDS( "s", 1d ),
    MINUTES( "m", 60d ),
    HOURS( "h", 3600d ),
    DAYS( "d", 86400d );

    private final String shortcut;
    private final Double multiplier;

    TimeUnitsEnum( String aShortcut, Double aMultiplier )
    {
        shortcut = aShortcut;
        multiplier = aMultiplier;
    }

    public static TimeUnitsEnum fromShortcut( final String aShortcut )
    {
        return Arrays.stream( values() )
                .filter( value -> value.getShortcut().equals( aShortcut ) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( "Unknown shortcut for weight enum: " + aShortcut ) );
    }
}
