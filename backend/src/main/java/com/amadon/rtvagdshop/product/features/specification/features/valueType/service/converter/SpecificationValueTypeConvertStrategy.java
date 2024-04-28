package com.amadon.rtvagdshop.product.features.specification.features.valueType.service.converter;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.features.valueType.entity.SpecificationValueType;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;

public interface SpecificationValueTypeConvertStrategy<T>
{
    /**
     * Converts value from entity to dto. Adds corresponding unit to converted value.
     *
     * @param productSpecification - specification entity
     *
     * @param aSpecificationDto - specification dto
     */
    void convertFromEntity( ProductSpecification productSpecification, ProductSpecificationDto<T> aSpecificationDto );

    /**
     * Converts value from dto to entity. Takes value unit under consideration and converts
     * dto value to value with atomic unit (e.g. 1kg to 1000g because g is default unit for weight)
     *
     * @param aSpecificationDto - specification dto
     *
     * @param productSpecification - specification entity
     */
    void convertFromDto( ProductSpecificationDto<T> aSpecificationDto, ProductSpecification productSpecification );

    /**
     * Determines if given converter can be used for converting value based on stored value type.
     *
     * @param specificationValueType - type of the specification
     *
     * @return true if converter can be used, false if not
     */
    boolean isApplicable( SpecificationValueType specificationValueType );
}
