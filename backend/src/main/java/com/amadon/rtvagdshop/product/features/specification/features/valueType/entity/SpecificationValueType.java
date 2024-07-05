package com.amadon.rtvagdshop.product.features.specification.features.valueType.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SpecificationValueType
{
    STRING( "string" ),
    BOOLEAN( "boolean" ),
    LINK( "link" ),
    WEIGHT( "weight" ),
    SINGLE_DIMENSION_SIZE( "singleDimensionSize" ),
    MULTI_DIMENSION_SIZE( "multiDimensionSize" ),
    TIME( "time" ),
    CAPACITY( "capacity" ),
    OTHER( "other" );

    private final String typeName;

    SpecificationValueType( final String typeName )
    {
        this.typeName = typeName;
    }

    public static SpecificationValueType fromValue( final String aTypeName )
    {
        return Arrays.stream( SpecificationValueType.values() )
                .filter( value -> value.getTypeName()
                        .equalsIgnoreCase( aTypeName ) )
                .findFirst()
                .orElseThrow( IllegalArgumentException::new );
    }
}
