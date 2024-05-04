package com.amadon.rtvagdshop.address.service.mapper;

import com.amadon.rtvagdshop.address.entity.Address;
import com.amadon.rtvagdshop.address.service.dto.AddressDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface AddressMapper
{
    Address toEntity( AddressDto addressDto );

    AddressDto toDto( Address address );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    Address partialUpdate( AddressDto addressDto, @MappingTarget Address address );
}