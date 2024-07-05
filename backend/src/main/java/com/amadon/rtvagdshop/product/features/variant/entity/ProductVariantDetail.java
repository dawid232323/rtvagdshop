package com.amadon.rtvagdshop.product.features.variant.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "product_variant_details" )
public class ProductVariantDetail
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_variant_details_id_gen" )
    @SequenceGenerator( name = "product_variant_details_id_gen", sequenceName = "product_variant_details_id_seq",
            allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Builder.Default
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne( fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false )
    @JoinColumn( name = "variant_category_id", nullable = false )
    private ProductVariantCategory variantCategory;

    @ColumnDefault( "true" )
    @Column( name = "is_available", nullable = false )
    private Boolean isAvailable;

    @ColumnDefault( "false" )
    @Column( name = "is_default", nullable = false )
    private Boolean isDefault;

    @Column( name = "code", nullable = false, length = 50 )
    private String code;

    @Column( name = "value", length = Integer.MAX_VALUE )
    private String value;

    @ColumnDefault( "0" )
    @Column( name = "additional_price", nullable = false )
    private Double additionalPrice;

}