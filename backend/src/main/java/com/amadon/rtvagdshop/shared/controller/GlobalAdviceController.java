package com.amadon.rtvagdshop.shared.controller;

import com.amadon.rtvagdshop.exception.entity.DomainArea;
import com.amadon.rtvagdshop.exception.entity.ReasonCode;
import com.amadon.rtvagdshop.exception.service.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalAdviceController extends ResponseEntityExceptionHandler
{

    @Override
    protected ResponseEntity< Object > handleMethodArgumentNotValid( final MethodArgumentNotValidException ex,
                                                                     final HttpHeaders headers,
                                                                     final HttpStatusCode status,
                                                                     final WebRequest request )
    {
        log.error( "Validation error", ex );
        final Map< String, String > exceptionDetails = getValidationExceptionDetails( ex );
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .area( DomainArea.GLOBAL )
                .reasonCode( ReasonCode.VALIDATION )
                .requestStatus( HttpStatus.BAD_REQUEST.value() )
                .message( "Validation error" )
                .errorDetails( exceptionDetails )
                .build();
        return ResponseEntity.badRequest()
                .body( errorResponse );
    }

    private Map< String, String > getValidationExceptionDetails( final MethodArgumentNotValidException aE )
    {
        final Map< String, String > exceptionDetails = new HashMap<>();
        if ( Objects.isNull( aE.getBindingResult() ) )
        {
            return exceptionDetails;
        }
        aE.getBindingResult()
                .getAllErrors()
                .forEach( error ->
                {
                    final String fieldName = ( (FieldError) error ).getField();
                    final String message = error.getDefaultMessage();
                    exceptionDetails.put( fieldName, message );
                } );
        return exceptionDetails;
    }
}
