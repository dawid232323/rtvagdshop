package com.amadon.rtvagdshop.product.features.specification.service.mapper;

import com.amadon.rtvagdshop.product.features.specification.entity.ProductSpecification;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationDto;
import com.amadon.rtvagdshop.product.features.specification.service.mapper.decorator.ProductSpecificationMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@DecoratedWith( ProductSpecificationMapperDecorator.class )
@Mapper( componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface ProductSpecificationMapper
{
    @Mapping( source = "value", target = "specificationValue", ignore = true )
    @Mapping( source = "specificationCategory.id", target = "specificationCategoryId" )
    @Mapping( source = "specificationCategory.name", target = "specificationCategoryName" )
    @Mapping( target = "type", expression = "java( productSpecification.getValueType().getTypeName() )" )
    @Mapping( target = "availableInVariants", expression = "java( productSpecification.getOnlyAvailableInVariants() )" )
    ProductSpecificationDto mapToDto( ProductSpecification productSpecification );

    @Mapping( source = "value", target = "value", ignore = true )
    @Mapping( source = "onlyAvailableInVariants", target = "onlyAvailableInVariants", ignore = true )
    ProductSpecification mapToEntityFromCreateDto( ProductSpecificationCreateDto aCreateDto );
}
