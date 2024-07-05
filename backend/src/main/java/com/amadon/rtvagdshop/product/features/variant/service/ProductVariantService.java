package com.amadon.rtvagdshop.product.features.variant.service;

import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantCategory;
import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantDetail;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantDetailCreateDto;
import com.amadon.rtvagdshop.product.features.variant.service.mapper.ProductVariantCategoryMapper;
import com.amadon.rtvagdshop.product.features.variant.service.mapper.ProductVariantDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantService
{
    private final ProductVariantCategoryMapper categoryMapper;
    private final ProductVariantDetailMapper detailMapper;

    public List< ProductVariantCategory > createVariantsForProduct( final List< ProductVariantCategoryCreateDto > aCategoryCreateDtos )
    {
        return aCategoryCreateDtos.stream()
                .map( this::resolveVariantCategory )
                .collect( Collectors.toList() );
    }


    private ProductVariantCategory resolveVariantCategory( final ProductVariantCategoryCreateDto aCreateDto )
    {
        final ProductVariantCategory variantCategory = categoryMapper.createFromDto( aCreateDto );
        variantCategory.setIsAvailable( true );
        final List< ProductVariantDetail > details = resolveProductVariantDetail( aCreateDto.getVariantDetails() );
        variantCategory.setVariantDetails( details );
        details.forEach( detail -> detail.setVariantCategory( variantCategory ) );
        return variantCategory;
    }

    private List< ProductVariantDetail > resolveProductVariantDetail( final List< ProductVariantDetailCreateDto > aCreateDtos )
    {
        return aCreateDtos.stream()
                .map( detailMapper::createFromDto )
                .peek( detail ->
                {
                    detail.setIsAvailable( true );
                } )
                .collect( Collectors.toList() );
    }
}
