package org.training.microservice.mscommon.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorObj handle(IllegalStateException exp) {
        return ErrorObj.builder()
                       .withDescParam(exp.getMessage())
                       .withErrorCodeParam(1010)
                       .build();
    }

    @ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorObj handle(RestClientResponseException exp) {
        return ErrorObj.builder()
                       .withDescParam(exp.getMessage())
                       .withErrorCodeParam(2055)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withDescParam("Validation error")
                       .withErrorCodeParam(1022)
                       .withSubErrorsParam(exp.getAllErrors()
                                              .stream()
                                              .map(se -> ErrorObj.builder()
                                                                 .withDescParam(se.toString())
                                                                 .withErrorCodeParam(1023)
                                                                 .build())
                                              .collect(Collectors.toList())
                       )
                       .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorObj handle(Exception exp) {
        logger.error("[ErrorAdvice][handle]-> *Error* : " + exp.getMessage(),exp);
        return ErrorObj.builder()
                       .withDescParam(exp.getMessage())
                       .withErrorCodeParam(5000)
                       .build();
    }

}
