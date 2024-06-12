package com.amadon.rtvagdshop.product.features.imageSlide.service.repository;

import com.amadon.rtvagdshop.product.features.imageSlide.entity.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSlideRepository extends JpaRepository< ImageSlide, Long >
{
    List< ImageSlide > findAllByProductId( final Long aProductId );
}