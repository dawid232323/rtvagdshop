package com.amadon.rtvagdshop.product.features.specification.entity;

import com.amadon.rtvagdshop.category.entity.Category;
import com.amadon.rtvagdshop.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "product_specification_categories" )
public class ProductSpecificationCategory implements Category
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_specification_categories_id_gen" )
    @SequenceGenerator( name = "product_specification_categories_id_gen", sequenceName =
            "product_specification_categories_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Builder.Default
    @Setter( AccessLevel.NONE )
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID()
            .toString();

    @Column( name = "name", nullable = false, length = 500 )
    private String name;

    @ManyToOne( fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL )
    @JoinColumn( name = "product_id", nullable = false )
    private Product product;

    @OneToMany( mappedBy = "specificationCategory", cascade = CascadeType.ALL )
    private List< ProductSpecification > productSpecifications;

    @Override
    public String getCode()
    {
        return null;
    }

    @Override
    public String getDisplayName()
    {
        return getName();
    }

    @Override
    public boolean hasParent()
    {
        return false;
    }

    @Nullable
    @Override
    public Category getParent()
    {
        return null;
    }
}