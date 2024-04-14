package com.amadon.rtvagdshop.product.features.specification.entity;

import lombok.Getter;

@Getter
public enum SpecificationValueType
{
    STRING( "string" ),
    BOOLEAN( "boolean" ),
    LINK( "link" ),
    WEIGHT( "weight" ),
    MULTI_DIMENSION_SIZE( "multiDimensionSize" ),
    SINGLE_DIMENSION_SIZE( "singleDimensionSize" ),
    TIME( "time" );

    private final String typeName;

    SpecificationValueType( final String typeName )
    {
        this.typeName = typeName;
    }

}
