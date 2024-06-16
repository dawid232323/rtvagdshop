package com.amadon.rtvagdshop.product.features.specification.service;

public interface ProductSpecificationIf<T>
{
    /**
     * Resolves specification unit.
     *
     * @return string representation of selected unit.
     */
    String getUnit();

    /**
     * Resolves value of the specification.
     *
     * @return value of the specification with type corresponding to the one in object definition.
     */
    T getSpecificationValue();
}
