package com.amadon.rtvagdshop.order.service;

import com.amadon.rtvagdshop.order.entity.Order;
import com.amadon.rtvagdshop.order.service.dto.OrderBuyerInformationDto;
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
    private final OrderBuyerInformationService buyerInformationService;
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

    /**
     * Updates order buyer information.
     *
     * @param aOrderId id of order to update
     *
     * @param aBuyerInformationDto dto with new data
     *
     * @return dto with all order data with newly updated buyer information
     */
    @Transactional
    public OrderDto updateOrderBuyerInformation( final Long aOrderId,
                                                 final OrderBuyerInformationDto aBuyerInformationDto )
    {
        log.debug( "Going to update buyer information for order {}", aOrderId );
        final Order requestedOrder = persistenceService.getOrderById( aOrderId );
        buyerInformationService.updateOrderBuyerInformation( requestedOrder.getOrderBuyerInformation(),
                aBuyerInformationDto );
        return orderMapper.toDto( persistenceService.saveOrder( requestedOrder ) );
    }
}
