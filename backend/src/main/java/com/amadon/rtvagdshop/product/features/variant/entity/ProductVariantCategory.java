package com.amadon.rtvagdshop.product.features.variant.entity;

import com.amadon.rtvagdshop.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "product_variant_categories" )
public class ProductVariantCategory
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_variant_categories_id_gen" )
    @SequenceGenerator( name = "product_variant_categories_id_gen", sequenceName = "product_variant_categories_id_seq"
            , allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Setter( AccessLevel.NONE )
    @Builder.Default
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID()
            .toString();

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false )
    @JoinColumn( name = "product_id", nullable = false )
    private Product product;

    @Column( name = "category_name", nullable = false, length = 500 )
    private String categoryName;

    @ColumnDefault( "true" )
    @Column( name = "is_available", nullable = false )
    private Boolean isAvailable;

    @OneToMany( mappedBy = "variantCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List< ProductVariantDetail > variantDetails;

}