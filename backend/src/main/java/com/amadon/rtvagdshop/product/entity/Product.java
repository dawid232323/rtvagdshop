package com.amadon.rtvagdshop.product.entity;

import com.amadon.rtvagdshop.product.features.category.entity.ProductCategory;
import com.amadon.rtvagdshop.product.features.description.entity.ProductDescription;
import com.amadon.rtvagdshop.product.features.imageSlide.entity.ImageSlide;
import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecificationCategory;
import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "products" )
public class Product
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "products_id_gen" )
    @SequenceGenerator( name = "products_id_gen", sequenceName = "products_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Setter( AccessLevel.NONE )
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @Column( name = "display_name", nullable = false, length = 500 )
    private String displayName;

    @Column( name = "base_price", nullable = false )
    private Long basePrice;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
    @JoinTable(
            name = "products_products_categories",
            joinColumns = { @JoinColumn( name = "product_category_id" ) },
            inverseJoinColumns = { @JoinColumn( name = "product_id" ) }
    )
    List< ProductCategory > categories;

    @OneToMany( mappedBy = "product" )
    private List< ProductSpecificationCategory > specificationCategories;

    @OneToMany( mappedBy = "product" )
    private List< ProductVariantCategory > variantCategories;

    @OneToOne( mappedBy = "product" )
    private ProductDescription description;

    @OneToMany( mappedBy = "product" )
    private List< ImageSlide > imageSlides;
}