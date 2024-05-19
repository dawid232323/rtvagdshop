package com.amadon.rtvagdshop.product.service.repository;

import com.amadon.rtvagdshop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository< Product, Long >, JpaSpecificationExecutor< Product >
{

}
