package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpGlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReaderNotFound.class)
    public ResponseEntity<Object> handleReaderNotFound(ReaderNotFound ex) {
        return new ResponseEntity<>("Reader not found!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReaderNotPossibleToAdd.class)
    public ResponseEntity<Object> handleReaderNotPossibleToAdd(ReaderNotPossibleToAdd ex) {
        return new ResponseEntity<>("Reader not possible to add. You should checking correct your data!!!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFound.class)
    public ResponseEntity<Object> handleTitleNotFound(TitleNotFound ex) {
        return new ResponseEntity<>("Title not found!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotPossibleToAdd.class)
    public ResponseEntity<Object> handleTitleNotPossibleToAdd(TitleNotPossibleToAdd ex) {
        return new ResponseEntity<>("Title not possible to add. You should checking correct your data!!!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CopyOfBookNotFound.class)
    public ResponseEntity<Object> handleCopyOfBookNotFound(Exception ex) {
        return new ResponseEntity<>("Copy of book not found!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookIsBorrowed.class)
    public ResponseEntity<Object> handleBookIsBorrowed(BookIsBorrowed ex) {
        return new ResponseEntity<>("Book is borrowed!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookIsNotLand.class)
    public ResponseEntity<Object> handleBookIsNotLand(BookIsNotLand ex) {
        return new ResponseEntity<>("Book is not land!", HttpStatus.BAD_REQUEST);
    }
}