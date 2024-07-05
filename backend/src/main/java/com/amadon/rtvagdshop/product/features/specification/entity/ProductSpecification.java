package com.amadon.rtvagdshop.product.features.specification.entity;

import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder( toBuilder = true )
@Table( name = "product_specifications" )
public class ProductSpecification
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_specifications_id_gen" )
    @SequenceGenerator( name = "product_specifications_id_gen", sequenceName = "product_specifications_id_seq",
            allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Builder.Default
    @Setter( AccessLevel.NONE )
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID()
            .toString();

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false )
    @JoinColumn( name = "specification_category_id", nullable = false )
    private ProductSpecificationCategory specificationCategory;

    @Column( name = "code", nullable = false, length = 50 )
    private String code;

    @Column( name = "display_name", nullable = false, length = 500 )
    private String displayName;

    @Column( name = "value_type", nullable = false, length = 100 )
    private SpecificationValueType valueType;

    @Column( name = "value", nullable = false, length = 100 )
    private String value;

    @ColumnDefault( "false" )
    @Column( name = "is_description_available" )
    private Boolean isDescriptionAvailable;

    @Setter( AccessLevel.NONE )
    @Getter( AccessLevel.NONE )
    @Column( name = "only_available_in_variants", length = Integer.MAX_VALUE )
    private String onlyAvailableInVariants;

    public List< String > getOnlyAvailableInVariants()
    {
        if ( Objects.isNull( onlyAvailableInVariants ) )
        {
            return new ArrayList<>();
        }
        return Arrays.stream( onlyAvailableInVariants.split( ";" ) )
                .toList();
    }

    public void setOnlyAvailableInVariants( final List< String > availableInVariants )
    {
        if ( availableInVariants == null || availableInVariants.isEmpty() )
        {
            this.onlyAvailableInVariants = null;
            return;
        }
        this.onlyAvailableInVariants = String.join( ";", availableInVariants );
    }
}