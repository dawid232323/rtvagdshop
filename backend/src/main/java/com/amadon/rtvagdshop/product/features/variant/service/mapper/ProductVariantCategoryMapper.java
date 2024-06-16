package com.amadon.rtvagdshop.product.features.variant.service.mapper;

import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantCategory;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantCategoryDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = { ProductVariantDetailMapper.class } )
public interface ProductVariantCategoryMapper
{
    ProductVariantCategory toEntity( ProductVariantCategoryDto productVariantCategoryDto );

    ProductVariantCategoryDto toDto( ProductVariantCategory productVariantCategory );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    ProductVariantCategory partialUpdate( ProductVariantCategoryDto productVariantCategoryDto,
                                          @MappingTarget ProductVariantCategory productVariantCategory );

    @Mapping( source = "variantDetails", target = "variantDetails", ignore = true )
    ProductVariantCategory createFromDto( ProductVariantCategoryCreateDto aCreateDto );
}