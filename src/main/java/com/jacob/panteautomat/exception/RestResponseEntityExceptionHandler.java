package com.jacob.panteautomat.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ MissingRequestHeaderException.class })
    public ResponseEntity<Object> handleMissingRequestHeaderException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Missing requestheader 'objectType'", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}