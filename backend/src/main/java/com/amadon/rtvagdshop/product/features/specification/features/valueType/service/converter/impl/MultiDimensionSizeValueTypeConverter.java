package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.units.entity.SizeUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.ProductSpecificationIf;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Component
@RequiredArgsConstructor
public class MultiDimensionSizeValueTypeConverter implements SpecificationValueTypeConvertStrategy< List< Double > >
{
    private final UnitCalculator< SizeUnitsEnum > unitCalculator;

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< List< Double > > aSpecificationDto )
    {
        final List< Double > values = getEntityValues( productSpecification.getValue() );
        final List< MultiSizeUnitDto > convertedSizes = getConvertedSizes( values );
        if ( areUnitsTheSame( convertedSizes ) )
        {
            fillDto( aSpecificationDto, convertedSizes );
            return;
        }
        normalizeValuesAndFillDto( aSpecificationDto, convertedSizes );
    }

    @Override
    public void convertFromDto( final ProductSpecificationIf< List< Double > > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        final String finalValue = getConvertedValuesFromDto( aSpecificationDto );
        productSpecification.setValue( finalValue );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.MULTI_DIMENSION_SIZE );
    }

    private void fillDto( final ProductSpecificationDto< List< Double > > aSpecificationDto,
                          final List< MultiSizeUnitDto > aConvertedSizes )
    {
        final List< Double > values = aConvertedSizes.stream()
                .map( MultiSizeUnitDto::value )
                .toList();
        final SizeUnitsEnum unit = aConvertedSizes.getFirst().unit;
        aSpecificationDto.setUnit( unit.getShortcut() );
        aSpecificationDto.setSpecificationValue( values );
    }

    private List< Double > getEntityValues( final String aValue )
    {
        if ( StringUtils.isEmpty( aValue ) )
        {
            throw new IllegalArgumentException( "Entity multi dimension size value cannot be null or empty" );
        }
        return Arrays.stream( aValue.split( "," ) )
                .map( Double::parseDouble )
                .toList();
    }

    private String getConvertedValuesFromDto( final ProductSpecificationIf< List< Double > > aSpecificationDto )
    {
        final SizeUnitsEnum currentUnit = SizeUnitsEnum.fromShortcut( aSpecificationDto.getUnit() );
        final List< String > convertedValues = aSpecificationDto.getSpecificationValue()
                .stream()
                .map( value -> unitCalculator.calculateDefault( currentUnit, value ) )
                .toList();
        return StringUtils.join( convertedValues, "," );
    }

    private List< MultiSizeUnitDto > getConvertedSizes( final List< Double > values )
    {
        return values.stream()
                .map( value ->
                {
                    final SizeUnitsEnum targetUnit = unitCalculator.calculateDisplayUnit( value );
                    final Double convertedValue = unitCalculator.convert( value, targetUnit,
                            unitCalculator.getDefaultUnit() );

                    return MultiSizeUnitDto.builder()
                            .value( convertedValue )
                            .unit( targetUnit )
                            .build();
                } )
                .toList();
    }

    private boolean areUnitsTheSame( final List< MultiSizeUnitDto > multiDimensionSizeUnits )
    {
        return multiDimensionSizeUnits
                .stream()
                .map( MultiSizeUnitDto::unit )
                .map( SizeUnitsEnum::getShortcut )
                .distinct()
                .toList()
                .size() == 1;
    }

    private SizeUnitsEnum getSmallestUnit( final List< MultiSizeUnitDto > multiDimensionSizeUnits )
    {
        return multiDimensionSizeUnits.stream()
                .map( MultiSizeUnitDto::unit )
                .min( Comparator.comparing( SizeUnitsEnum::getMultiplier ) )
                .orElse( unitCalculator.getDefaultUnit() );
    }

    private void normalizeValuesAndFillDto( final ProductSpecificationDto< List< Double > > aSpecificationDto,
                                            final List< MultiSizeUnitDto > aConvertedSizes )
    {
        final SizeUnitsEnum targetUnit = getSmallestUnit( aConvertedSizes );
        final List< MultiSizeUnitDto > normalisedValues = aConvertedSizes.stream()
                .map( val -> MultiSizeUnitDto.builder()
                        .unit( targetUnit )
                        .value( unitCalculator.convert( val.value(), targetUnit, val.unit() ) )
                        .build() )
                .toList();
        fillDto( aSpecificationDto, normalisedValues );
    }


    @Builder
    private record MultiSizeUnitDto( Double value, SizeUnitsEnum unit )
    {
    }
}
