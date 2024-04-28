package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl;

import com.amadon.rtvagdshop.product.features.specification.features.units.entity.WeightUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl.WeightUnitCalculator;
import com.amadon.rtvagdshop.testUtil.CamelCaseTestDisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DisplayNameGeneration( CamelCaseTestDisplayNameGenerator.class )
public class WeightUnitCalculatorTest
{
    private final WeightUnitCalculator weightUnitCalculator = new WeightUnitCalculator();

    @Test
    public void itShouldCalculateKilogramAsDisplayUnit()
    {
        final Double valueInKilograms = 5000000.0;
        final WeightUnitsEnum displayUnit = weightUnitCalculator.calculateDisplayUnit( valueInKilograms );
        assertEquals( "Display unit should be in kilograms", WeightUnitsEnum.KILOGRAM.getShortcut(),
                displayUnit.getShortcut() );
    }

    @Test
    public void itShouldCalculateGramsAsDisplayUnit()
    {
        final Double valueInGrams = 0.05;
        final WeightUnitsEnum displayUnit = weightUnitCalculator.calculateDisplayUnit( valueInGrams );
        assertEquals( "Display unit should be in milligrams", WeightUnitsEnum.MILLIGRAM.getShortcut(),
                displayUnit.getShortcut() );
    }

    @Test
    public void itShouldCalculateDecagramsAsDisplayUnit()
    {
        final Double valueInGrams = 3450.0;
        final WeightUnitsEnum displayUnit = weightUnitCalculator.calculateDisplayUnit( valueInGrams );
        assertEquals( "Display unit should be in grams", WeightUnitsEnum.GRAM.getShortcut(),
                displayUnit.getShortcut() );
    }

    @Test
    public void itShouldCalculateCorrectAmountOfGrams()
    {
        final Double kilogramsValue = 5.34;
        final WeightUnitsEnum kilogramUnit = WeightUnitsEnum.KILOGRAM;
        final String gramsValue = weightUnitCalculator.calculateDefault( kilogramUnit, kilogramsValue );
        assertEquals( "Value in grams should be correctly calculated", "5340000", gramsValue );
    }
}
