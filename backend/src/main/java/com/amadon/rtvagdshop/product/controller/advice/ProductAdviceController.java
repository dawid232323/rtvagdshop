package com.amadon.rtvagdshop.product.controller.advice;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import com.amadon.rtvagdshop.product.service.exception.LackOfCreateStrategyException;
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

    @ResponseBody
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler( LackOfCreateStrategyException.class )
    public ErrorResponse handleLackOfCreateStrategyException( final LackOfCreateStrategyException aE )
    {
        log.error( "Couldn't find suitable product create strategy.", aE );
        return ErrorResponse.builder()
                .uuid( UUID.randomUUID().toString() )
                .message( aE.getMessage() )
                .area( DomainArea.PRODUCT )
                .reasonCode( ReasonCode.LACK_OF_CREATE_STRATEGY )
                .requestStatus( HttpStatus.INTERNAL_SERVER_ERROR.value() )
                .build();
    }
}
