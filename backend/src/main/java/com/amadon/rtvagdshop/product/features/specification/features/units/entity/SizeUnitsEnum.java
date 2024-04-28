package com.amadon.rtvagdshop.product.features.specification.features.units.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SizeUnitsEnum implements UnitValue
{

    MILLIMITER( "mm", 1d ),
    CENTIMITER( "cm", 10d ),
    METER( "m", 1000d ),
    KILOMETER( "km", 1000000d );

    private final String shortcut;
    private final Double multiplier;

    SizeUnitsEnum( String shortcut, Double multiplier )
    {
        this.shortcut = shortcut;
        this.multiplier = multiplier;
    }

    public static SizeUnitsEnum fromShortcut( final String aShortcut )
    {
        return Arrays.stream( values() )
                .filter( value -> value.getShortcut().equals( aShortcut ) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( "Unknown shortcut for weight enum: " + aShortcut ) );
    }
}
