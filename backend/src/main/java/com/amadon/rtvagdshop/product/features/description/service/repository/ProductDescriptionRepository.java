package com.amadon.rtvagdshop.product.features.description.service.repository;

import com.amadon.rtvagdshop.product.features.description.entity.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDescriptionRepository extends JpaRepository< ProductDescription, Long >
{
    Optional< ProductDescription > findByProductId( final Long aProductId );
}
