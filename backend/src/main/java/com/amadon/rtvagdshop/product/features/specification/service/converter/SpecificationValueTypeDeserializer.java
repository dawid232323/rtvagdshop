package com.amadon.rtvagdshop.product.features.specification.service.converter;

import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SpecificationValueTypeDeserializer extends JsonDeserializer< SpecificationValueType >
{

    @Override
    public SpecificationValueType deserialize( final JsonParser aJsonParser, final DeserializationContext aDeserializationContext ) throws
            IOException, JacksonException
    {
        final String value = aJsonParser.getText();
        return SpecificationValueType.fromValue( value );
    }
}
