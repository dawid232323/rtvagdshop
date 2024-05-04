package com.amadon.rtvagdshop.order.service.mapper;

import com.amadon.rtvagdshop.order.entity.OrderProductInformation;
import com.amadon.rtvagdshop.order.service.dto.OrderProductInformationDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = { ProductInfoSelectedVariantMapper.class } )
public interface OrderProductInformationMapper
{
    OrderProductInformation toEntity( OrderProductInformationDto orderProductInformationDto );

    @AfterMapping
    default void linkProductInfoSelectedVariants( @MappingTarget OrderProductInformation orderProductInformation )
    {
        orderProductInformation.getProductInfoSelectedVariants()
                .forEach( productInfoSelectedVariant -> productInfoSelectedVariant.setProductInfo( orderProductInformation ) );
    }

    OrderProductInformationDto toDto( OrderProductInformation orderProductInformation );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    OrderProductInformation partialUpdate( OrderProductInformationDto orderProductInformationDto,
                                           @MappingTarget OrderProductInformation orderProductInformation );
}