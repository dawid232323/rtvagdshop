package com.amadon.rtvagdshop.order.controller;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import com.amadon.rtvagdshop.exception.service.dto.ErrorResponse;
import com.amadon.rtvagdshop.order.service.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class OrderAdviceController
{
    @ResponseBody
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ExceptionHandler( OrderNotFoundException.class )
    public ErrorResponse handleOrderNotFoundException( final OrderNotFoundException aOrderNotFoundException )
    {
        log.error( "Couldn't find order with id {}", aOrderNotFoundException.getOrderId(), aOrderNotFoundException );
        return ErrorResponse.builder()
                .requestStatus( HttpStatus.NOT_FOUND.value() )
                .reasonCode( ReasonCode.OBJECT_NOT_FOUND )
                .area( DomainArea.ORDER )
                .message( "Could not find order with id " + aOrderNotFoundException.getOrderId() )
                .build();
    }
}
