package com.amadon.rtvagdshop.product.entity;

import com.amadon.rtvagdshop.product.features.category.entity.ProductCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
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
}