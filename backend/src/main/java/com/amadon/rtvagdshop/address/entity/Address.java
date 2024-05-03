package com.amadon.rtvagdshop.address.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table( name = "addresses" )
public class Address
{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "addresses_id_gen" )
    @SequenceGenerator( name = "addresses_id_gen", sequenceName = "addresses_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Column( name = "country", nullable = false, length = 100 )
    private String country;

    @Column( name = "postal_code", length = 100 )
    private String postalCode;

    @Column( name = "city", length = 100 )
    private String city;

    @Column( name = "street", length = 300 )
    private String street;

    @Column( name = "house_number", length = 50 )
    private String houseNumber;

    @Column( name = "flat_number", length = 50 )
    private String flatNumber;

}