package com.amadon.rtvagdshop.product.service;

import com.amadon.rtvagdshop.product.entity.repository.ProductCategoryRepository;
import com.amadon.rtvagdshop.product.service.dto.ProductCategoryDto;
import com.amadon.rtvagdshop.product.service.mapper.ProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService
{
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public List< ProductCategoryDto > findAllActiveProductCategories()
    {
        return productCategoryRepository
                .findAllActiveCategories()
                .stream()
                .map( productCategoryMapper::mapToDto )
                .collect( Collectors.toList() );
    }
}
