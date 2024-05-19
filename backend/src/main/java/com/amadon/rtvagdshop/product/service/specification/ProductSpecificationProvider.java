package com.amadon.rtvagdshop.product.service.specification;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.category.entity.ProductCategory;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchQueryDto;
import com.amadon.rtvagdshop.shared.search.QuerySpecificationProvider;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProductSpecificationProvider implements QuerySpecificationProvider< ProductSearchQueryDto, Product >
{
    @Override
    public Specification< Product > getSpecification( final ProductSearchQueryDto aQueryParams )
    {
        Specification< Product > baseSpec = noSpec();
        if ( !Objects.isNull( aQueryParams.getCategoryId() ) && aQueryParams.getCategoryId() > 0 )
        {
            baseSpec = baseSpec.and( withCategoryIn( aQueryParams.getCategoryId() ) );
        }
        return baseSpec;
    }

    private Specification< Product > withCategoryIn( final Long aCategoryId )
    {
        return ( ( root, query, criteriaBuilder ) ->
        {
            final Join< Product, ProductCategory > categoryJoin = root.join( "categories" );
            return criteriaBuilder.equal( categoryJoin.get( "id" ), aCategoryId );
        } );
    }
}
