package com.amadon.rtvagdshop.product.service;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchQueryDto;
import com.amadon.rtvagdshop.product.service.exception.ProductNotFoundException;
import com.amadon.rtvagdshop.product.service.repository.ProductRepository;
import com.amadon.rtvagdshop.shared.search.QuerySpecificationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class ProductPersistenceService
{
    private final ProductRepository productRepository;
    private final QuerySpecificationProvider< ProductSearchQueryDto, Product > specificationProvider;

    public Product getProduct( final Long aProductId )
    {
        return productRepository.findById( aProductId )
                .orElseThrow( () -> new ProductNotFoundException( aProductId ) );
    }

    public Page< Product > searchForProducts( final ProductSearchQueryDto aSearchQueryDto, final Pageable aPageable )
    {
        final Specification< Product > specification = specificationProvider.getSpecification( aSearchQueryDto );
        return productRepository.findAll( specification, aPageable);
    }

    public List< Product > searchForProducts( final ProductSearchQueryDto aSearchQueryDto )
    {
        final Specification< Product > specification = specificationProvider.getSpecification( aSearchQueryDto );
        return productRepository.findAll( specification);
    }

    public Product saveProduct( final Product aProduct )
    {
        productRepository.save( aProduct );
        return aProduct;
    }
}

