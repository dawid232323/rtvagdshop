package com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator;


import com.amadon.rtvagdshop.product.features.specification.features.units.entity.UnitValue;

public interface UnitCalculator< T extends UnitValue >
{
    /**
     * Calculates value converted to default converter unit value.
     *
     * @param currentUnit  enum with unit of the passed value
     * @param currentValue value to calculate
     *
     * @return string representation of default value calculated from passed unit and value
     */
    default String calculateDefault( T currentUnit, Double currentValue )
    {
        return String.valueOf( currentUnit.getMultiplier() * currentValue );
    }

    /**
     * Converts passed value to value in target unit. Calculates value's unit on it's own.
     *
     * @param currentValue value to convert
     * @param targetUnit   target unit
     *
     * @return passed value converted to passed target unit
     */
    default Double convert( final Double currentValue, T targetUnit )
    {
        final T currentUnit = calculateDisplayUnit( currentValue );
        return convert( currentValue, targetUnit, currentUnit );
    }

    /**
     * Converts passed value to value in target unit.
     *
     * @param currentValue value to convert
     * @param targetUnit   target unit
     * @param currentUnit  unit of value to convert
     *
     * @return passed value converted to passed target unit
     */
    default Double convert( final Double currentValue, T targetUnit, T currentUnit )
    {
        if ( currentUnit.equals( targetUnit ) )
        {
            return currentValue;
        }
        final Double defaultUnitValue = currentUnit.getMultiplier() * currentValue;
        return defaultUnitValue / targetUnit.getMultiplier();
    }

    /**
     * Resolves best unit to display stored value. E.g. for 2000 millimeters it will return Meters.
     *
     * @param currentValue value to determine unit for
     *
     * @return Most suitable unit type
     */
    T calculateDisplayUnit( Double currentValue );

    /**
     * Resolves default unit for implemented calculator type.
     *
     * @return default unit for implemented calculator type
     */
    T getDefaultUnit();
}
