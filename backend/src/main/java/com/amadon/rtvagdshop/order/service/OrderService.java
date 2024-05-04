package com.amadon.rtvagdshop.order.service;

import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;
import com.amadon.rtvagdshop.order.service.dto.OrderDto;
import com.amadon.rtvagdshop.order.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService
{

    private final OrderPersistenceService persistenceService;
    private final OrderMapper orderMapper;

    /**
     * Creates new order based on necessary data in create dto.
     *
     * @param aCreateDto dto with necessary data for creating new order
     *
     * @return newly created order
     */
    @Transactional
    public OrderDto createOrder( final OrderCreateDto aCreateDto )
    {
        log.debug( "Going to create order with product id {}", aCreateDto.getProductId() );
        final Order createdOrder = persistenceService.createNewOrder( aCreateDto );
        return orderMapper.toDto( createdOrder );
    }
}
