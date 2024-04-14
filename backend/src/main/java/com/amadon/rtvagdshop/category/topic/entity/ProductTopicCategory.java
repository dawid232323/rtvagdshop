package com.amadon.rtvagdshop.category.topic.entity;

import com.amadon.rtvagdshop.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "product_topic_category" )
public class ProductTopicCategory implements Category
{
    @Id
    @Setter( AccessLevel.NONE )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "id_generator" )
    @SequenceGenerator( name = "id_generator", sequenceName = "product_topic_category_id_seq", allocationSize = 1 )
    @Column( name = "id" )
    private Long id;

    @Column( name = "uuid" )
    @Setter( AccessLevel.NONE )
    private String uuid = UUID.randomUUID().toString();

    @Column( name = "code", length = 50, nullable = false, unique = true )
    private String code;

    @Column( name = "display_name", length = 500, nullable = false )
    private String displayName;

    @ManyToOne
    @JoinColumn( name = "product_topic_id", nullable = false )
    private ProductTopic topic;
}
