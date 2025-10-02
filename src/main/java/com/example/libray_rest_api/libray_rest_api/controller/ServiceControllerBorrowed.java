package com.example.libray_rest_api.libray_rest_api.controller;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.BorrowedServiceDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceControllerBorrowed {
    List<BorrowedServiceDto> getAll();
    ResponseEntity<BorrowedServiceDto> borrowTitle(String wantTitle, Long readerId);
    void returnBook(Long copyId);
}