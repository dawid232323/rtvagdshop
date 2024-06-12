package com.amadon.rtvagdshop.product.features.imageSlide.service;

import com.amadon.rtvagdshop.product.features.imageSlide.service.dto.ImageSlideDto;
import com.amadon.rtvagdshop.product.features.imageSlide.service.mapper.ImageSlideMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageSlideService
{
    private final ImageSlidePersistenceService slidePersistenceService;
    private final ImageSlideMapper slideMapper;

    public List< ImageSlideDto > getAllByProductId( final Long aProductId )
    {
        return slidePersistenceService.getAllByProductId( aProductId )
                .stream()
                .map( slideMapper::toDto )
                .toList();
    }
}
