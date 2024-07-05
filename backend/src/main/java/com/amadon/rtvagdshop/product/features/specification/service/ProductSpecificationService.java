package com.amadon.rtvagdshop.product.features.specification.service;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecificationCategory;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationCategoryMapper;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSpecificationService
{

    private final ProductSpecificationMapper specificationMapper;
    private final ProductSpecificationCategoryMapper categoryMapper;

    public void createSpecificationForProduct(
            final List< ProductSpecificationCategoryCreateDto > aCategoryCreateDtos, final Product aProduct )
    {
        final List< ProductSpecificationCategory > specificationCategories = aCategoryCreateDtos.stream()
                .map( this::resolveSpecificationCategory )
                .collect( Collectors.toList() );
        setProductRelations( specificationCategories, aProduct );
    }

    private ProductSpecificationCategory resolveSpecificationCategory( final ProductSpecificationCategoryCreateDto createCategoryDto )
    {
        final ProductSpecificationCategory category = categoryMapper.mapToEntity( createCategoryDto );
        final List< ProductSpecification > specifications =
                resolveProductSpecifications( createCategoryDto.getProductSpecifications() );
        category.setProductSpecifications( specifications );
        specifications.forEach( specification -> specification.setSpecificationCategory( category ) );
        return category;
    }

    private List< ProductSpecification > resolveProductSpecifications( final List< ProductSpecificationCreateDto > aCreateDtos )
    {
        return aCreateDtos.stream()
                .map( specificationMapper::mapToEntityFromCreateDto )
                .collect( Collectors.toList() );
    }

    private void setProductRelations( final List< ProductSpecificationCategory > aSpecificationCategories,
                                      final Product aProduct )
    {
        aProduct.setSpecificationCategories( aSpecificationCategories );
        aSpecificationCategories.forEach( category -> category.setProduct( aProduct ) );
    }
}
