package com.amadon.rtvagdshop.product.controller;

import com.amadon.rtvagdshop.product.features.specification.service.dto.ProductSpecificationCategoryCreateDto;
import com.amadon.rtvagdshop.product.features.variant.service.dto.ProductVariantCategoryCreateDto;
import com.amadon.rtvagdshop.product.service.ProductService;
import com.amadon.rtvagdshop.product.service.dto.InitProductDto;
import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchQueryDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchResultDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping( value = "/api/products" )
public class ProductController
{
    private final ProductService productService;

    @GetMapping( value = "/{productId}" )
    public ProductDto getProductById( @PathVariable( "productId" ) final Long productId )
    {
        return productService.getProductDtoById( productId );
    }

    @GetMapping
    public Page< ProductSearchResultDto > getProductEntries( @Valid @ModelAttribute final ProductSearchQueryDto aSearchQueryDto,
                                                             final Pageable aPageable )
    {
        return productService.searchForProducts( aSearchQueryDto, aPageable );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public ProductDto initProduct( @Valid @RequestBody final InitProductDto aInitProductDto )
    {
        return productService.initProduct( aInitProductDto );
    }

    @PostMapping( value = "/variants/{productId}" )
    @ResponseStatus( HttpStatus.CREATED )
    public ProductDto createProductVariants( @Valid @RequestBody final List< ProductVariantCategoryCreateDto > aCreateDtos,
                                             @PathVariable( "productId" ) final Long aProductId )
    {
        return productService.createProductVariants( aCreateDtos, aProductId );
    }

    @PostMapping( value = "/specifications/{productId}" )
    @ResponseStatus( HttpStatus.CREATED )
    public ProductDto createProductSpecifications( @Valid @RequestBody final List< ProductSpecificationCategoryCreateDto > aCreateDtos,
                                                   @PathVariable( "productId" ) final Long aProductId )
    {
        return productService.createProductSpecifications( aCreateDtos, aProductId );
    }
}
