package com.amadon.rtvagdshop.product.service.mapper;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.category.service.mapper.ProductCategoryMapper;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationCategoryMapper;
import com.amadon.rtvagdshop.product.features.variant.service.mapper.ProductVariantCategoryMapper;
import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy =
        NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = { ProductCategoryMapper.class, ProductSpecificationCategoryMapper.class, ProductVariantCategoryMapper.class } )
public interface ProductMapper
{
    ProductDto mapToDto( Product product );
}
