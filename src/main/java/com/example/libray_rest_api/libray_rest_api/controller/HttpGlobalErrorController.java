package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.exception.ReaderNotFound;
import com.example.libray_rest_api.libray_rest_api.domain.exception.ReaderNotPossibleToAdd;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpGlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReaderNotFound.class)
    public ResponseEntity<Object> handleReaderNotFound(ReaderNotFound ex) {
        return new ResponseEntity<>("Reader not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReaderNotPossibleToAdd.class)
    public ResponseEntity<Object> handleReaderNotPossibleToAdd(ReaderNotPossibleToAdd ex) {
        return new ResponseEntity<>("Reader not possible to add. You should correct your data!!!", HttpStatus.BAD_REQUEST);
    }
}
