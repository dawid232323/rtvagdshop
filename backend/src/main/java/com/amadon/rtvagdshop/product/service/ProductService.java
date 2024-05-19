package com.amadon.rtvagdshop.product.service;

import com.amadon.rtvagdshop.category.entity.Category;
import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.service.dto.CategoryPathDto;
import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchQueryDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchResultDto;
import com.amadon.rtvagdshop.product.service.exception.ProductNotFoundException;
import com.amadon.rtvagdshop.product.service.mapper.ProductMapper;
import com.amadon.rtvagdshop.product.service.repository.ProductRepository;
import com.amadon.rtvagdshop.product.service.util.CategoryPathBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductMapper productMapper;
    private final ProductPersistenceService persistenceService;
    private final CategoryPathBuilder categoryPathBuilder;

    public ProductDto getProductDtoById( final Long aProductId )
    {
        return productMapper.mapToDto( persistenceService.getProduct( aProductId ) );
    }

    public Product getProduct( final Long aProductId )
    {
        return persistenceService.getProduct( aProductId );
    }

    public Page< ProductSearchResultDto > searchForProducts( final ProductSearchQueryDto aSearchQueryDto,
                                                             final Pageable aPageable )
    {
        final Page< Product > results = persistenceService.searchForProducts( aSearchQueryDto, aPageable );
        return getSearchResults( aSearchQueryDto, results );
    }

    private Page< ProductSearchResultDto > getSearchResults( final ProductSearchQueryDto aSearchQueryDto,
                                                             final Page< Product > results )
    {
        final Long aCategoryId = aSearchQueryDto.getCategoryId();
        if ( Objects.isNull( aCategoryId ) )
        {
            return results.map( productMapper::mapToSearchResult );
        }
        return results.map( result ->
        {
            final ProductSearchResultDto mappedResult = productMapper.mapToSearchResult( result );
            final List< CategoryPathDto > categoryPaths =
                    categoryPathBuilder.buildCategoryPath( result.getCategories(), aCategoryId );
            mappedResult.setCategoryPaths( categoryPaths );
            return mappedResult;
        } );
    }
}
