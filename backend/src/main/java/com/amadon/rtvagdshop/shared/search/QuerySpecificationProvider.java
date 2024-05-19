package com.amadon.rtvagdshop.shared.search;

import org.springframework.data.jpa.domain.Specification;

/**
 * @param <T> object containing all available query params
 * @param <K> type of entity ti be queried
 */
public interface QuerySpecificationProvider< T, K >
{
    Specification< K > getSpecification( T aQueryParams );

    default Specification< K > noSpec()
    {
        return ( ( root, query, criteriaBuilder ) -> criteriaBuilder.isNotNull( root.get( "id" ) ) );
    }
}
