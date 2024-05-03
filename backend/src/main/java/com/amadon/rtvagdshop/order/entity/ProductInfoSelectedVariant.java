package com.amadon.rtvagdshop.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table( name = "product_info_selected_variants" )
public class ProductInfoSelectedVariant
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_info_selected_variants_id_gen" )
    @SequenceGenerator( name = "product_info_selected_variants_id_gen", sequenceName =
            "product_info_selected_variants_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "product_info_id" )
    private OrderProductInformation productInfo;

    @Column( name = "variant_category_id", nullable = false )
    private Long variantCategoryId;

    @Column( name = "variant_category_name", nullable = false, length = 500 )
    private String variantCategoryName;

    @Column( name = "variant_id", nullable = false )
    private Long variantId;

    @Column( name = "variant_code", nullable = false, length = 500 )
    private String variantCode;

    @Column( name = "variant_value", nullable = false, length = 500 )
    private String variantValue;

    @Column( name = "additional_price", nullable = false )
    private Double additionalPrice;

}