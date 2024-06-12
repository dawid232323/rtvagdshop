package com.amadon.rtvagdshop.product.service.creator.impl;

import com.amadon.rtvagdshop.product.features.category.service.ProductCategoryService;
import com.amadon.rtvagdshop.product.service.creator.AbstractProductCreator;
import org.springframework.stereotype.Component;

@Component
public class DefaultProductCreatorStrategy extends AbstractProductCreator
{
    public DefaultProductCreatorStrategy( final ProductCategoryService aCategoryService )
    {
        super( aCategoryService );
    }

    @Override
    public boolean isApplicable()
    {
        return true;
    }
}
