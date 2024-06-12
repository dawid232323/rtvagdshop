package com.amadon.rtvagdshop.product.service.exception;

public class LackOfCreateStrategyException extends RuntimeException
{
    public LackOfCreateStrategyException()
    {
        super( "Could not find create strategy for desired product" );
    }
}
