package com.amadon.rtvagdshop.product.features.description.service.exception;

public class ProductDescriptionNotFoundException extends RuntimeException
{
    public ProductDescriptionNotFoundException( final long aProductDescriptionId )
    {
        super( "Product description with id " + aProductDescriptionId + " not found" );
    }

    public ProductDescriptionNotFoundException( final String aMessage )
    {
        super( aMessage );
    }
}
