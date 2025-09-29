package com.example.libray_rest_api.libray_rest_api.domain.enums;


public enum StatusCopyOfBook {
    IN_CIRCULATION,
    DAMAGED,
    LOST,
    BORROWED;

    public static StatusCopyOfBook determineStatus(String status) {
        switch (status.trim().toUpperCase()) {
            case "IN_CIRCULATION":
                return StatusCopyOfBook.IN_CIRCULATION;
            case "DAMAGED":
                return StatusCopyOfBook.DAMAGED;
            case "LOST":
                return StatusCopyOfBook.LOST;
            case "BORROWED":
                return StatusCopyOfBook.BORROWED;
            default:
                return StatusCopyOfBook.IN_CIRCULATION;
        }
    }
}