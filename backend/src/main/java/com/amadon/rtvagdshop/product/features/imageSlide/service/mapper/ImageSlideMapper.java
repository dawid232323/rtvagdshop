package com.amadon.rtvagdshop.product.features.imageSlide.service.mapper;

import com.amadon.rtvagdshop.product.features.imageSlide.entity.ImageSlide;
import com.amadon.rtvagdshop.product.features.imageSlide.service.dto.ImageSlideDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface ImageSlideMapper
{
    ImageSlide toEntity( ImageSlideDto imageSlideDto );

    ImageSlideDto toDto( ImageSlide imageSlide );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    ImageSlide partialUpdate( ImageSlideDto imageSlideDto, @MappingTarget ImageSlide imageSlide );
}