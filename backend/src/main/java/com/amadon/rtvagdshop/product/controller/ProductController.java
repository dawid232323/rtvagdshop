package com.amadon.rtvagdshop.product.controller;

import com.amadon.rtvagdshop.product.service.ProductService;
import com.amadon.rtvagdshop.product.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
