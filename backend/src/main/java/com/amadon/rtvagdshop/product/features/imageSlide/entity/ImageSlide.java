package com.amadon.rtvagdshop.product.features.imageSlide.entity;

import com.amadon.rtvagdshop.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name = "product_image_slides" )
public class ImageSlide
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "products_image_slide_id_gen" )
    @SequenceGenerator( name = "products_image_slide_id_gen", sequenceName = "product_image_slides_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Setter( AccessLevel.NONE )
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "product_id", nullable = false )
    private Product product;

    @Column( name = "image_path", length = 200, nullable = false )
    private String imagePath;

    @Column( name = "is_cover", nullable = false )
    private boolean isCover = false;
}
