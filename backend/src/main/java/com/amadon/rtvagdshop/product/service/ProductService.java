package com.amadon.rtvagdshop.product.service;

import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import com.amadon.rtvagdshop.product.service.exception.ProductNotFoundException;
import com.amadon.rtvagdshop.product.service.mapper.ProductMapper;
import com.amadon.rtvagdshop.product.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductDto getProductById( final Long aProductId )
    {
        return productRepository.findById( aProductId )
                .map( productMapper::mapToDto )
                .orElseThrow( () -> new ProductNotFoundException( aProductId ) );
    }
}
