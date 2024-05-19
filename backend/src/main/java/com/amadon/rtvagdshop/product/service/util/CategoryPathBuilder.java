package com.amadon.rtvagdshop.product.service.util;

import com.amadon.rtvagdshop.category.entity.Category;
import com.amadon.rtvagdshop.product.service.dto.CategoryPathDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryPathBuilder
{
    public < T extends Category > List< CategoryPathDto > buildCategoryPath( final List< T > aStartingCategories,
                                                                             final Long aStartCategoryId )
    {
        final Category startCategory = getStartCategory( aStartingCategories, aStartCategoryId );
        return deepFindRelatedCategories( startCategory, new LinkedList<>() );
    }

    private < T extends Category > Category getStartCategory( final List< T > aStartingCategories,
                                                              final Long aStartCategoryId )
    {
        return aStartingCategories.stream()
                .filter( aCategory -> aCategory.getId()
                        .equals( aStartCategoryId ) )
                .findFirst()
                .orElseThrow( IllegalAccessError::new );
    }

    private < T extends Category > List< CategoryPathDto > deepFindRelatedCategories( final T aStartingCategory,
                                                                                      final List< CategoryPathDto > aCategoryPathDtos )
    {
        if ( aStartingCategory.hasParent() )
        {
            return deepFindRelatedCategories( aStartingCategory.getParent(), aCategoryPathDtos );
        }
        final CategoryPathDto mappedCategory = mapCategory( aStartingCategory );
        aCategoryPathDtos.add( mappedCategory );
        return aCategoryPathDtos;
    }

    private < T extends Category > CategoryPathDto mapCategory( final T aCategory )
    {
        return CategoryPathDto.builder()
                .categoryId( aCategory.getId() )
                .categoryName( aCategory.getDisplayName() )
                .build();
    }
}
