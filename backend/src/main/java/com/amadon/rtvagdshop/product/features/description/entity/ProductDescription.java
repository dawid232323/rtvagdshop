package com.amadon.rtvagdshop.product.features.description.entity;

import com.amadon.rtvagdshop.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "product_descriptions" )
public class ProductDescription
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_descriptions_id_gen" )
    @SequenceGenerator( name = "product_descriptions_id_gen", sequenceName = "product_descriptions_id_seq",
            allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @NotNull
    @OneToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "product_id", nullable = false )
    private Product product;

    @Column( name = "description", length = Integer.MAX_VALUE )
    private String description;

}