package com.amadon.rtvagdshop.product.features.specification.features.units.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WeightUnitsEnum implements UnitValue
{
    KILOGRAM( "kg", 1000000.0 ),
    DECAGRAMM( "dag", 100000.0 ),
    GRAM( "g", 1000.0 ),
    MILLIGRAM( "mg", 1.0 );

    private final String shortcut;
    private final Double multiplier;

    WeightUnitsEnum( final String aShortcut, final Double aMultiplier )
    {
        shortcut = aShortcut;
        multiplier = aMultiplier;
    }

    public static WeightUnitsEnum fromShortcut( final String aShortcut )
    {
        return Arrays.stream( values() )
                .filter( value -> value.getShortcut().equals( aShortcut ) )
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException( "Unknown shortcut for weight enum: " + aShortcut ) );
    }
}
