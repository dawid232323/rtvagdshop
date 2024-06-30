package com.amadon.rtvagdshop.product.features.specification.service.validator.ipml;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.validator.ProductSpecificationValidatorRule;
import com.amadon.rtvagdshop.product.features.specification.service.validator.exception.SpecificationVariantDoesNotExistException;
import com.amadon.rtvagdshop.product.features.variant.entity.ProductVariantDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductSpecificationVariantValidatorRule implements ProductSpecificationValidatorRule
{
    @Override
    public void validate( final ProductSpecificationCreateDto aCreateDto, final Product aProduct )
    {
        if ( aCreateDto.getOnlyAvailableInVariants()
                .isEmpty() )
        {
            return;
        }

        final Set< String > variantCodes = getExistingVariantCodes( aProduct );
        final Set< String > specificationVariantCodes = getSpecificationVariantCodes( aCreateDto );

        if ( variantCodes.containsAll( specificationVariantCodes ) )
        {
            return;
        }

        specificationVariantCodes.removeAll( variantCodes );
        throw new SpecificationVariantDoesNotExistException( specificationVariantCodes.stream()
                .toList()
                .getFirst(), aCreateDto.getCode(), aCreateDto.getDisplayName() );
    }

    private Set< String > getExistingVariantCodes( final Product aProduct )
    {
        return aProduct.getVariantCategories()
                .stream()
                .flatMap( aVariantCategory -> aVariantCategory.getVariantDetails()
                        .stream() )
                .map( ProductVariantDetail::getCode )
                .collect( Collectors.toSet() );
    }

    private Set< String > getSpecificationVariantCodes( final ProductSpecificationCreateDto aCreateDto )
    {
        return Set.copyOf( aCreateDto.getOnlyAvailableInVariants() );
    }
}
