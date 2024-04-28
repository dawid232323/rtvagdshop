package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.units.entity.SizeUnitsEnum;
import com.amadon.rtvagdshop.product.features.specification.features.units.service.calculator.UnitCalculator;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SingleDimensionSizeValueTypeConverter implements SpecificationValueTypeConvertStrategy< Double >
{

    private final UnitCalculator< SizeUnitsEnum > sizeUnitCalculator;

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< Double > aSpecificationDto )
    {
        final Double value = Double.parseDouble( productSpecification.getValue() );
        final SizeUnitsEnum displayUnit = sizeUnitCalculator.calculateDisplayUnit( value );
        final Double convertedValue = sizeUnitCalculator.convert( value, displayUnit,
                sizeUnitCalculator.getDefaultUnit() );

        aSpecificationDto.setSpecificationValue( convertedValue );
        aSpecificationDto.setUnit( displayUnit.getShortcut() );
    }

    @Override
    public void convertFromDto( final ProductSpecificationDto< Double > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        final SizeUnitsEnum currentUnit = SizeUnitsEnum.fromShortcut( aSpecificationDto.getUnit() );
        final String defaultValue = sizeUnitCalculator.calculateDefault( currentUnit,
                aSpecificationDto.getSpecificationValue() );
        productSpecification.setValue( defaultValue );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.SINGLE_DIMENSION_SIZE );
    }
}
