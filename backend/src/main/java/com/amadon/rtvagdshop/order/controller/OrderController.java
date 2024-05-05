package com.amadon.rtvagdshop.order.controller;

import com.amadon.rtvagdshop.order.service.OrderService;
import com.amadon.rtvagdshop.order.service.dto.OrderBuyerInformationDto;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;
import com.amadon.rtvagdshop.order.service.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/orders" )
public class OrderController
{
    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder( @RequestBody final OrderCreateDto aCreateDto )
    {
        return orderService.createOrder( aCreateDto );
    }

    @PatchMapping( "/{orderId}/update-buyer-info" )
    public OrderDto updateOrderBuyerInformation( @PathVariable( "orderId" ) final Long aOrderId,
                                                 @Valid @RequestBody final OrderBuyerInformationDto aBuyerInformationDto )
    {
        return orderService.updateOrderBuyerInformation( aOrderId, aBuyerInformationDto );
    }
}
