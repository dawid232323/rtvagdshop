package com.amadon.rtvagdshop.product.features.variant.service.mapper;

import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantDetail;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantDetailDto;
import org.mapstruct.*;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING )
public interface ProductVariantDetailMapper
{
    ProductVariantDetail toEntity( ProductVariantDetailDto productVariantDetailDto );

    ProductVariantDetailDto toDto( ProductVariantDetail productVariantDetail );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    ProductVariantDetail partialUpdate( ProductVariantDetailDto productVariantDetailDto,
                                        @MappingTarget ProductVariantDetail productVariantDetail );
}