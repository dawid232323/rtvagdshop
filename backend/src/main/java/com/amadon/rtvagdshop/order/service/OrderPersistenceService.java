package com.amadon.rtvagdshop.order.service;

import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.service.creator.OrderCreateStrategy;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;
import com.amadon.rtvagdshop.order.service.exception.OrderNotFoundException;
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
        saveOrder( createdOrder );
        return createdOrder;
    }

    /**
     * Retrieves requested order with specified id.
     *
     * @param aOrderId id of order to retrieve.
     *
     * @return {@link Order} entity with given id. If not found, throws {@link OrderNotFoundException}
     */
    public Order getOrderById( final Long aOrderId )
    {
        return orderRepository.findById( aOrderId )
                .orElseThrow( () -> new OrderNotFoundException( aOrderId ) );
    }

    /**
     * Saves order.
     *
     * @param aOrder order to save
     *
     * @return newly saved entity
     */
    public Order saveOrder( final Order aOrder )
    {
        return orderRepository.save( aOrder );
    }

    private OrderCreateStrategy resolveCreateStrategy()
    {
        return orderCreateStrategies.stream()
                .filter( OrderCreateStrategy::isApplicable )
                .findFirst()
                .orElseThrow();
    }
}
