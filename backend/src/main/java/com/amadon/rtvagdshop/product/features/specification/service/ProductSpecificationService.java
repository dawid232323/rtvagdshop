package com.amadon.rtvagdshop.product.features.specification.service;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecificationCategory;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationCategoryMapper;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSpecificationService
{

    private final ProductSpecificationMapper specificationMapper;
    private final ProductSpecificationCategoryMapper categoryMapper;

    public List< ProductSpecificationCategory > createSpecificationForProduct(
            final List< ProductSpecificationCategoryCreateDto > aCategoryCreateDtos )
    {
        return aCategoryCreateDtos.stream()
                .map( this::resolveSpecificationCategory )
                .toList();
    }

    private ProductSpecificationCategory resolveSpecificationCategory( final ProductSpecificationCategoryCreateDto createCategoryDto )
    {
        final ProductSpecificationCategory category = categoryMapper.mapToEntity( createCategoryDto );
        final List< ProductSpecification > specifications =
                resolveProductSpecifications( createCategoryDto.getProductSpecifications() );
        category.setProductSpecifications( specifications );
        return category;
    }

    private List< ProductSpecification > resolveProductSpecifications( final List< ProductSpecificationCreateDto > aCreateDtos )
    {
        return aCreateDtos.stream()
                .map( specificationMapper::mapToEntityFromCreateDto )
                .toList();
    }
}
