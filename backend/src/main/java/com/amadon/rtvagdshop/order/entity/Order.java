package com.amadon.rtvagdshop.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name = "orders" )
public class Order
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "orders_id_gen" )
    @SequenceGenerator( name = "orders_id_gen", sequenceName = "orders_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @CreationTimestamp
    @Column( name = "created_at" )
    private Instant createdAt;

    @UpdateTimestamp
    @Column( name = "updated_at" )
    private Instant updatedAt;

    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL )
    private OrderBuyerInformation orderBuyerInformation;

    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL )
    private OrderProductInformation orderProductInformation;

}