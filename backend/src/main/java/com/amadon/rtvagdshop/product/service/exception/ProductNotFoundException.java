package com.amadon.rtvagdshop.product.service.exception;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException( final long productId )
    {
        super( "Product with id " + productId + " not found" );
    }

    public ProductNotFoundException( final String aMessage )
    {
        super( aMessage );
    }
}
