package com.amadon.rtvagdshop.product.features.imageSlide.controller;

import com.amadon.rtvagdshop.product.features.imageSlide.service.ImageSlideService;
import com.amadon.rtvagdshop.product.features.imageSlide.service.dto.ImageSlideDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping( value = "/api/products/image-slides" )
public class ImageSlideController
{
    private final ImageSlideService imageSlideService;

    @GetMapping( value = "/{productId}" )
    public List< ImageSlideDto > getAllForProduct( @PathVariable( "productId" ) final Long aProductId )
    {
        return imageSlideService.getAllByProductId( aProductId );
    }
}
