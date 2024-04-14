package com.amadon.rtvagdshop.product.controller;

import com.amadon.rtvagdshop.product.service.ProductCategoryService;
import com.amadon.rtvagdshop.product.service.dto.ProductCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController( value = "/product-categories" )
public class ProductCategoryController
{

    private final ProductCategoryService productCategoryService;

    @GetMapping( value = "/active" )
    public List< ProductCategoryDto > getProductCategories()
    {
        return productCategoryService.findAllActiveProductCategories();
    }
}
