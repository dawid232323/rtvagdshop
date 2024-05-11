package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.impl;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LinkValueTypeConverter implements SpecificationValueTypeConvertStrategy< String >
{
    private final String hostProtocol;

    public LinkValueTypeConverter( @Value( "${shop.host.protocol}" ) final String aHostProtocol )
    {
        this.hostProtocol = aHostProtocol;
    }

    @Override
    public void convertFromEntity( final ProductSpecification productSpecification,
                                   final ProductSpecificationDto< String > aSpecificationDto )
    {
        aSpecificationDto.setSpecificationValue( productSpecification.getValue() );
        aSpecificationDto.setUnit( hostProtocol );
    }

    @Override
    public void convertFromDto( final ProductSpecificationDto< String > aSpecificationDto,
                                final ProductSpecification productSpecification )
    {
        productSpecification.setValue( aSpecificationDto.getSpecificationValue() );
    }

    @Override
    public boolean isApplicable( final SpecificationValueType specificationValueType )
    {
        return specificationValueType.equals( SpecificationValueType.LINK );
    }
}
