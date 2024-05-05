package com.amadon.rtvagdshop.order.service.mapper;

import com.amadon.rtvagdshop.address.service.mapper.AddressMapper;
import com.amadon.rtvagdshop.order.entity.OrderBuyerInformation;
import com.amadon.rtvagdshop.order.service.dto.OrderBuyerInformationDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = { AddressMapper.class } )
public interface OrderBuyerInformationMapper
{
    OrderBuyerInformation toEntity( OrderBuyerInformationDto orderBuyerInformationDto );

    OrderBuyerInformationDto toDto( OrderBuyerInformation orderBuyerInformation );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping( target = "id", ignore = true )
    void partialUpdate( OrderBuyerInformationDto orderBuyerInformationDto,
                                         @MappingTarget OrderBuyerInformation orderBuyerInformation );
}