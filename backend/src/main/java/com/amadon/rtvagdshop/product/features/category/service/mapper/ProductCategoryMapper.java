package com.amadon.rtvagdshop.product.features.category.service.mapper;

import com.amadon.rtvagdshop.product.features.category.entity.ProductCategory;
import com.amadon.rtvagdshop.product.features.category.service.dto.ProductCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper( componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface ProductCategoryMapper
{
    @Mapping( source = "topicCategory.id", target = "topicCategoryId" )
    @Mapping( target = "topicCategoryName", source = "topicCategory.displayName" )
    @Mapping( target = "topicId", source = "topicCategory.topic.id" )
    @Mapping( target = "topicName", source = "topicCategory.topic.displayName" )
    ProductCategoryDto mapToDto( ProductCategory productCategory );
}
