package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl;

import com.amadon.rtvagdshop.product.features.specification.features.units.entity.SizeUnitsEnum;
import com.amadon.rtvagdshop.testUtil.CamelCaseTestDisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayNameGeneration( CamelCaseTestDisplayNameGenerator.class )
public class SizeUnitCalculatorTest
{
    private final SizeUnitCalculator sizeUnitCalculator = new SizeUnitCalculator();

    @Test
    public void itShouldCalculateMetersAsDisplayUnit()
    {
        final double value = 250d;
        final SizeUnitsEnum unit = sizeUnitCalculator.calculateDisplayUnit( value );
        assertEquals( "Display unit shortcut should be \"m\"", SizeUnitsEnum.CENTIMITER.getShortcut(), unit.getShortcut() );
    }

    @Test
    public void itShouldCalculateMillimitersAsDisplayUnit()
    {
        final double value = 3d;
        final SizeUnitsEnum unit = sizeUnitCalculator.calculateDisplayUnit( value );
        assertEquals( "Display unit shortcut should be \"mm\"", SizeUnitsEnum.MILLIMITER.getShortcut(),
                unit.getShortcut() );
    }

    @Test
    public void itShouldCalculateCorrectAmountOfDefaultValue()
    {
        final double value = 250d;
        final SizeUnitsEnum unit = SizeUnitsEnum.METER;
        final String defaultValue = sizeUnitCalculator.calculateDefault( unit, value );
        assertEquals( "Default value should be string with correct value and int type", "250000", defaultValue );
    }

    @MethodSource
    @ParameterizedTest
    public void shouldCorrectlyCalculateBetweenDifferentUnits( final String currentUnitShortcut,
                                                               final String targetUnitShortcut,
                                                               final Double currentValue,
                                                               final Double expectedValue )
    {
        final SizeUnitsEnum currentUnit = SizeUnitsEnum.fromShortcut( currentUnitShortcut );
        final SizeUnitsEnum targetUnit = SizeUnitsEnum.fromShortcut( targetUnitShortcut );
        final Double convertedValue = sizeUnitCalculator.convert( currentValue, targetUnit, currentUnit );
        final String testMessage = String.format( "Converted value in %s should be equal to expected one in  %s",
                currentUnit.getShortcut(), targetUnit.getShortcut() );
        assertEquals( testMessage, expectedValue, convertedValue );
    }

    private static Stream< Arguments > shouldCorrectlyCalculateBetweenDifferentUnits()
    {
        return Stream.of(
                Arguments.of( "cm", "m", 100d, 1d ),
                Arguments.of( "m", "mm", 1d, 1000d ),
                Arguments.of( "cm", "m", 15d, 0.15d ),
                Arguments.of( "km", "cm", 2d, 200000d )
        );
    }
}
