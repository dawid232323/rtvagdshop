package com.amadon.rtvagdshop.product.controller;

import com.amadon.rtvagdshop.product.service.ProductService;
import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchQueryDto;
import com.amadon.rtvagdshop.product.service.dto.ProductSearchResultDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
}
