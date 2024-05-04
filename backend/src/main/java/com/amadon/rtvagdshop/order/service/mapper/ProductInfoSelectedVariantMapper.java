package com.amadon.rtvagdshop.order.service.mapper;

import com.amadon.rtvagdshop.order.entity.ProductInfoSelectedVariant;
import com.amadon.rtvagdshop.order.service.dto.ProductInfoSelectedVariantDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface ProductInfoSelectedVariantMapper
{
    ProductInfoSelectedVariant toEntity( ProductInfoSelectedVariantDto productInfoSelectedVariantDto );

    ProductInfoSelectedVariantDto toDto( ProductInfoSelectedVariant productInfoSelectedVariant );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    ProductInfoSelectedVariant partialUpdate( ProductInfoSelectedVariantDto productInfoSelectedVariantDto,
                                              @MappingTarget ProductInfoSelectedVariant productInfoSelectedVariant );
}