package com.amadon.rtvagdshop.order.service.mapper;

import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.entity.OrderBuyerInformation;
import com.amadon.rtvagdshop.order.entity.OrderProductInformation;
import com.amadon.rtvagdshop.order.service.dto.OrderDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = { OrderBuyerInformationMapper.class, OrderProductInformationMapper.class } )
public interface OrderMapper
{
    Order toEntity( OrderDto orderDto );

    @AfterMapping
    default void linkOrderBuyerInformation( @MappingTarget Order order )
    {
        OrderBuyerInformation orderBuyerInformation = order.getOrderBuyerInformation();
        if ( orderBuyerInformation != null )
        {
            orderBuyerInformation.setOrder( order );
        }
    }

    @AfterMapping
    default void linkOrderProductInformation( @MappingTarget Order order )
    {
        OrderProductInformation orderProductInformation = order.getOrderProductInformation();
        if ( orderProductInformation != null )
        {
            orderProductInformation.setOrder( order );
        }
    }

    OrderDto toDto( Order order );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    Order partialUpdate( OrderDto orderDto, @MappingTarget Order order );
}