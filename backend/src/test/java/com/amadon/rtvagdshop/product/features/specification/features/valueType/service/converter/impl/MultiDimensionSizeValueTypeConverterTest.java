package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.units.entity.SizeUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.impl.SizeUnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import com.amadon.rtvagdshop.testUtil.CamelCaseTestDisplayNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.*;

@DisplayNameGeneration( CamelCaseTestDisplayNameGenerator.class )
public class MultiDimensionSizeValueTypeConverterTest
{
    private final MultiDimensionSizeValueTypeConverter multiDimensionSizeValueTypeConverter;

    MultiDimensionSizeValueTypeConverterTest()
    {
        final UnitCalculator< SizeUnitsEnum > weightUnitCalculator = new SizeUnitCalculator();
        multiDimensionSizeValueTypeConverter = new MultiDimensionSizeValueTypeConverter( weightUnitCalculator );
    }

    @Test
    public void shouldBeApplicableOnlyForMultiSizeSpecifications()
    {
        final ProductSpecification productSpecification = getPrefilledProductSpecification();
        final boolean isApplicableForMultiSize =
                multiDimensionSizeValueTypeConverter.isApplicable( productSpecification.getValueType() );
        productSpecification.setValueType( SpecificationValueType.SINGLE_DIMENSION_SIZE );
        final boolean isApplicableForSingleSize =
                multiDimensionSizeValueTypeConverter.isApplicable( productSpecification.getValueType() );

        assertTrue( "Converter should be available for multi dimension size", isApplicableForMultiSize );
        assertFalse( "Converter should not be available for single dimension size", isApplicableForSingleSize );
    }

    @Test
    public void itShouldFillDtoWithNormalisedValues()
    {

        final Set< Double > convertedValues = Set.of( 50d, 100d, 200d );
        final ProductSpecification productSpecification = getPrefilledProductSpecification();
        final ProductSpecificationDto< List< Double > > specificationDto = new ProductSpecificationDto<>();
        multiDimensionSizeValueTypeConverter.convertFromEntity( productSpecification, specificationDto );

        final String normalisedUnit = specificationDto.getUnit();
        final String targetUnitShortcut = SizeUnitsEnum.CENTIMITER.getShortcut();
        assertEquals( "Normalised unit should be equal " + targetUnitShortcut, targetUnitShortcut, normalisedUnit );
        specificationDto.getSpecificationValue()
                .forEach( value -> assertTrue( "Normalised value should be one of " + convertedValues,
                        convertedValues.contains( value ) )
                );
    }

    private ProductSpecification getPrefilledProductSpecification()
    {
        return ProductSpecification.builder()
                .id( 123L )
                .code( "TMP" )
                .displayName( "Test" )
                .valueType( SpecificationValueType.MULTI_DIMENSION_SIZE )
                .value( "500,1000,2000" )
                .build();
    }
}
