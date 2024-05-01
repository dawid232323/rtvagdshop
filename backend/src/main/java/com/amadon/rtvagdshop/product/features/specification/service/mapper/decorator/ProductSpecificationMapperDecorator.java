package com.amadon.rtvagdshop.product.features.specification.service.mapper.decorator;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter.SpecificationValueTypeConvertStrategy;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class ProductSpecificationMapperDecorator implements ProductSpecificationMapper
{
    @Autowired
    @Qualifier( "delegate" )
    private ProductSpecificationMapper mapperDelegate;

    @Autowired
    private List< SpecificationValueTypeConvertStrategy< ? > > convertStrategies;

    @Override
    public ProductSpecificationDto< ? > mapToDto( final ProductSpecification productSpecification )
    {
        final ProductSpecificationDto productSpecificationDto = mapperDelegate.mapToDto( productSpecification );
        final SpecificationValueTypeConvertStrategy< ? > strategy =
                resolveStrategy( productSpecification.getValueType() );
        strategy.convertFromEntity( productSpecification, productSpecificationDto );
        return productSpecificationDto;
    }

    private SpecificationValueTypeConvertStrategy< ? > resolveStrategy( final SpecificationValueType aValueType )
    {
        return convertStrategies.stream()
                .filter( strategy -> strategy.isApplicable( aValueType ) )
                .findFirst()
                .orElseThrow( IllegalArgumentException::new );
    }
}
