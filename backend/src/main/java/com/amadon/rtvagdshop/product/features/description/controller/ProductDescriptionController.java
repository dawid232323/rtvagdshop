package com.amadon.rtvagdshop.product.features.description.controller;

import com.amadon.rtvagdshop.product.features.description.service.ProductDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping( value = "/api/products/descriptions" )
public class ProductDescriptionController
{

    private final ProductDescriptionService productDescriptionService;

    @GetMapping( value = "/{productId}", produces = MediaType.TEXT_HTML_VALUE  )
    public String getProductDescription( @PathVariable( "productId" ) final Long aProductId )
    {
        return productDescriptionService.getDescriptionResponseByProductId( aProductId );
    }
}
