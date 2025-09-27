package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.exception.ReaderNotFound;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpGlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleReaderNotFound(ReaderNotFound ex) {
        return new ResponseEntity<>("Reader not found", HttpStatus.NOT_FOUND);
    }
}
