package com.amadon.rtvagdshop.product.entity.repository;

import com.amadon.rtvagdshop.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository< ProductCategory, Long >
{
    @Query(
            "select pc from ProductCategory pc " +
            "left join fetch pc.topicCategory left join fetch pc.topicCategory.topic " +
            "order by pc.topicCategory.topic.displayName, pc.topicCategory.displayName, pc.displayName"
    )
    List< ProductCategory > findAllActiveCategories();
}
