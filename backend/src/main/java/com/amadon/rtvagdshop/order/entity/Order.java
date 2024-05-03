package com.amadon.rtvagdshop.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table( name = "orders" )
public class Order
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "orders_id_gen" )
    @SequenceGenerator( name = "orders_id_gen", sequenceName = "orders_id_seq", allocationSize = 1 )
    @ColumnDefault( "nextval('orders_id_seq'" )
    @Column( name = "id", nullable = false )
    private Long id;

    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid;

    @Column( name = "created_at", nullable = false )
    private Instant createdAt;

    @Column( name = "updated_at" )
    private Instant updatedAt;

    @OneToOne( mappedBy = "order" )
    private OrderBuyerInformation orderBuyerInformation;

    @OneToOne( mappedBy = "order" )
    private OrderProductInformation orderProductInformation;

}