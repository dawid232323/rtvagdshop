package com.amadon.rtvagdshop.product.features.imageSlide.service;

import com.amadon.rtvagdshop.product.features.imageSlide.entity.ImageSlide;
import com.amadon.rtvagdshop.product.features.imageSlide.service.repository.ImageSlideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ImageSlidePersistenceService
{
    private final ImageSlideRepository imageSlideRepository;

    public List< ImageSlide > getAllByProductId( final Long aProductId )
    {
        return this.imageSlideRepository.findAllByProductId( aProductId );
    }
}
