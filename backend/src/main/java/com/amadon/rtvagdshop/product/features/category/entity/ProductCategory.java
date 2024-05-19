package com.amadon.rtvagdshop.product.features.category.entity;

import com.amadon.rtvagdshop.category.entity.Category;
import com.amadon.rtvagdshop.category.features.topic.entity.ProductTopicCategory;
import com.amadon.rtvagdshop.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table( name = "product_category" )
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory implements Category
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_category_id_gen" )
    @SequenceGenerator( name = "product_category_id_gen", sequenceName = "product_category_id_seq", allocationSize = 1 )
    @Column( name = "id", nullable = false )
    private Long id;

    @Setter( AccessLevel.NONE )
    @Column( name = "uuid", nullable = false, length = 50 )
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne( fetch = FetchType.EAGER, optional = false )
    @JoinColumn( name = "topic_category_id", nullable = false )
    private ProductTopicCategory topicCategory;

    @Column( name = "code", nullable = false, length = 50 )
    private String code;

    @Column( name = "display_name", nullable = false, length = 500 )
    private String displayName;

    @ManyToMany( mappedBy = "categories", fetch = FetchType.LAZY )
    private List< Product > products;

    @Nullable
    @Override
    public Category getParent()
    {
        return topicCategory;
    }
}