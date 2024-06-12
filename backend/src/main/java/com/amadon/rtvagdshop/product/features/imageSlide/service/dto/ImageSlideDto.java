package com.amadon.rtvagdshop.product.features.imageSlide.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.amadon.rtvagdshop.product.features.imageSlide.entity.ImageSlide}
 */
@Getter
@Setter
public class ImageSlideDto implements Serializable
{
    private Long id;
    private String imagePath;
    @JsonProperty( "isCover" )
    private boolean isCover = false;
}