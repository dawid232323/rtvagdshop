package com.amadon.rtvagdshop.product.service.mapper;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.category.service.mapper.ProductCategoryMapper;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.ProductSpecificationCategoryMapper;
import com.amadon.rtvagdshop.product.features.variant.service.mapper.ProductVariantCategoryMapper;
import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchQueryDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchResultDto;
import org.mapstruct.*;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING, nullValueCheckStrategy =
        NullValueCheckStrategy.ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = { ProductCategoryMapper.class, ProductSpecificationCategoryMapper.class, ProductVariantCategoryMapper.class }
)
public interface ProductMapper
{
    ProductDto mapToDto( Product product );

    @Mapping( source = "displayName", target = "name" )
    @Mapping( ignore = true, target = "categoryPaths" )
    ProductSearchResultDto mapToSearchResult( Product aProduct );
}
