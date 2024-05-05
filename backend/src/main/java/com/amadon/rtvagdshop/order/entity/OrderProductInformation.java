package com.amadon.rtvagdshop.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "order_product_information" )
public class OrderProductInformation
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "order_product_information_id_gen" )
    @SequenceGenerator( name = "order_product_information_id_gen", sequenceName = "order_product_information_id_seq",
            allocationSize = 1 )
    @ColumnDefault( "nextval('order_product_information_id_seq'" )
    @Column( name = "id", nullable = false )
    private Long id;

    @Builder.Default
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "order_id" )
    private Order order;

    @Column( name = "product_id", nullable = false )
    private Long productId;

    @Column( name = "product_name", nullable = false, length = 500 )
    private String productName;

    @Column( name = "base_price", nullable = false )
    private Double basePrice;

    @Column( name = "order_code", nullable = false, length = 300 )
    private String orderCode;

    @OneToMany( mappedBy = "productInfo", cascade = CascadeType.ALL )
    private List< ProductInfoSelectedVariant > productInfoSelectedVariants = new ArrayList<>();

}