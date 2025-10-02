package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.BorrowedService;

import java.util.Optional;

public interface ServiceDataBaseForBorrowed {
    BorrowedService save(BorrowedService borrowedService);
    Optional<BorrowedService> findById(Long id);
}