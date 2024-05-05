package com.amadon.rtvagdshop.order.service.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException
{
    private final Long orderId;

    public OrderNotFoundException( final Long aOrderId, final String aMessage )
    {
        super( aMessage );
        orderId = aOrderId;
    }

    public OrderNotFoundException( final Long aOrderId )
    {
        super( "Could not find requested order" );
        orderId = aOrderId;
    }
}
