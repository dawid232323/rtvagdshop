package com.amadon.rtvagdshop.order.service;

import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.service.creator.OrderCreateStrategy;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;
import com.amadon.rtvagdshop.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class OrderPersistenceService
{
    private final OrderRepository orderRepository;
    private final List< OrderCreateStrategy > orderCreateStrategies;

    /**
     * Creates new order using applicable strategy.
     *
     * @param aCreateDto dto with necessary data for order creation
     *
     * @return newly created order
     */
    public Order createNewOrder( final OrderCreateDto aCreateDto )
    {
        final OrderCreateStrategy createStrategy = resolveCreateStrategy();
        log.debug( "Going to create new order with strategy: {}", createStrategy.getClass() );
        final Order createdOrder = createStrategy.create( aCreateDto );
        orderRepository.save( createdOrder );
        return createdOrder;
    }

    private OrderCreateStrategy resolveCreateStrategy()
    {
        return orderCreateStrategies.stream()
                .filter( OrderCreateStrategy::isApplicable )
                .findFirst()
                .orElseThrow();
    }
}
