package com.amadon.rtvagdshop.product.features.specification.service.mapper;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecificationCategory;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = { ProductSpecificationMapper.class }
)
public interface ProductSpecificationCategoryMapper
{
    @Mapping( source = "productSpecifications", target = "specifications" )
    ProductSpecificationCategoryDto mapToDto( ProductSpecificationCategory productSpecificationCategory );
}
