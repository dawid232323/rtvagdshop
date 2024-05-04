package com.amadon.rtvagdshop.order.service.repository;

import com.amadon.rtvagdshop.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository< Order, Long >
{
}
