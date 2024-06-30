package com.amadon.rtvagdshop.product.features.specification.service.validator.exception;

public class SpecificationVariantDoesNotExistException extends RuntimeException
{
    public SpecificationVariantDoesNotExistException( final String aMessage )
    {
        super( aMessage );
    }

    public SpecificationVariantDoesNotExistException( final String aVariantCode, final String aSpecificationCode,
                                                      final String aProductName )
    {
        super( String.format( "Could not create specification with code %s for product %s " +
                "- variant with code %s does not exist", aSpecificationCode, aProductName, aVariantCode ) );
    }
}
