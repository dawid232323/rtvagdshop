package com.amadon.rtvagdshop.product.features.description.controller;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import com.amadon.rtvagdshop.exception.service.dto.ErrorResponse;
import com.amadon.rtvagdshop.product.features.description.service.exception.ProductDescriptionNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ProductDescriptionControllerAdvice
{
    @ResponseBody
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ExceptionHandler( ProductDescriptionNotFoundException.class )
    public ErrorResponse handleProductDescriptionNotFoundException( final ProductDescriptionNotFoundException aE )
    {
        log.error( aE.getMessage(), aE );
        return ErrorResponse.builder()
                .uuid( UUID.randomUUID().toString() )
                .message( aE.getMessage() )
                .area( DomainArea.PRODUCT_DESCRIPTION )
                .reasonCode( ReasonCode.OBJECT_NOT_FOUND )
                .requestStatus( HttpStatus.NOT_FOUND.value() )
                .build();
    }
}
