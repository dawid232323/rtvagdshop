package com.amadon.rtvagdshop.product.service.creator;


import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.category.entity.ProductCategory;
import com.amadon.rtvagdshop.product.features.category.service.ProductCategoryService;
import com.amadon.rtvagdshop.product.service.dto.InitProductDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractProductCreator implements ProductCreatorStrategy
{
    private final ProductCategoryService categoryService;

    @Override
    public Product createProduct( final InitProductDto aInitProductDto )
    {
        final List< ProductCategory > categories = getCategories( aInitProductDto );
        final Product product = getInitializedProduct( aInitProductDto );
        product.setCategories( categories );
        return product;
    }

    private List< ProductCategory > getCategories( final InitProductDto aDto )
    {
        return categoryService.getProductCategoriesByIds( aDto.getCategoryIds() );
    }

    private Product getInitializedProduct( final InitProductDto aDto )
    {
        return Product.builder()
                .displayName( aDto.getDisplayName() )
                .basePrice( aDto.getBasePrice() )
                .build();
    }
}
