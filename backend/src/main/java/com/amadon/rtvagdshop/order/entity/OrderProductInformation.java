package com.amadon.rtvagdshop.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
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

    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid;

    @OneToOne( fetch = FetchType.EAGER )
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

    @OneToMany( mappedBy = "productInfo" )
    private List< ProductInfoSelectedVariant > productInfoSelectedVariants = new ArrayList<>();

}