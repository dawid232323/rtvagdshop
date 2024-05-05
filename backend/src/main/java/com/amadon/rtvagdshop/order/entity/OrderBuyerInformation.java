package com.amadon.rtvagdshop.order.entity;

import com.amadon.rtvagdshop.address.entity.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name = "order_buyer_information" )
public class OrderBuyerInformation
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "order_buyer_information_id_gen" )
    @SequenceGenerator( name = "order_buyer_information_id_gen", sequenceName = "order_buyer_information_id_seq",
            allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "order_id" )
    private Order order;

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "shipping_address_id" )
    private Address shippingAddress;

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn( name = "correspondence_address_id" )
    private Address correspondenceAddress;

    @Column( name = "first_name", length = 400 )
    private String firstName;

    @Column( name = "last_name", length = 400 )
    private String lastName;

    @Column( name = "email", length = 100 )
    private String email;

    @Column( name = "phone_number", length = 100 )
    private String phoneNumber;

    @ColumnDefault( "false" )
    @Column( name = "is_invoice_required" )
    private Boolean isInvoiceRequired;

    @Column( name = "nip", length = 50 )
    private String nip;

    @Column( name = "regon", length = 50 )
    private String regon;

    @Column( name = "company_name", length = 500 )
    private String companyName;

}