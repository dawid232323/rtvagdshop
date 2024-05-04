package com.amadon.rtvagdshop.order.controller;

import com.amadon.rtvagdshop.order.service.OrderService;
import com.amadon.rtvagdshop.order.service.dto.OrderCreateDto;
import com.amadon.rtvagdshop.order.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
