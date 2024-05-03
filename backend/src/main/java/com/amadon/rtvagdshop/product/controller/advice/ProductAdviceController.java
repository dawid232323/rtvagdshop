package com.amadon.rtvagdshop.product.controller.advice;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import com.amadon.rtvagdshop.product.service.exception.ProductNotFoundException;
import com.amadon.rtvagdshop.exception.service.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Slf4j
@ControllerAdvice
public class ProductAdviceController
{
    @ResponseBody
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ExceptionHandler( ProductNotFoundException.class )
    public ErrorResponse handleProductNotFoundException( final ProductNotFoundException ex )
    {
        log.error( "Could not find requested product. Original error message was: {}", ex.getMessage(), ex );
        return ErrorResponse.builder()
                .uuid( UUID.randomUUID().toString() )
                .message( ex.getMessage() )
                .area( DomainArea.PRODUCT )
                .reasonCode( ReasonCode.OBJECT_NOT_FOUND )
                .requestStatus( HttpStatus.NOT_FOUND.value() )
                .build();
    }
}
