package com.amadon.rtvagdshop.product.features.specification.service.validator;

import com.amadon.rtvagdshop.product.entity.Product;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSpecificationValidatorRuleEngine
{
    private final List< ProductSpecificationValidatorRule > validatorRules;

    public void validate( final List< ProductSpecificationCategoryCreateDto > aCreateDtos, final Product aProduct )
    {
        final List< ProductSpecificationCreateDto > specificationCreateDtos = retrieveCreateDtos( aCreateDtos );
        specificationCreateDtos.forEach( aCreateDto -> validatorRules.forEach( rule -> rule.validate( aCreateDto,
                aProduct ) ) );
    }

    private List< ProductSpecificationCreateDto > retrieveCreateDtos(
            final List< ProductSpecificationCategoryCreateDto > aCategoryCreateDtos )
    {
        return aCategoryCreateDtos
                .stream()
                .flatMap( aCreateDto -> aCreateDto.getProductSpecifications()
                        .stream() )
                .toList();
    }
}
