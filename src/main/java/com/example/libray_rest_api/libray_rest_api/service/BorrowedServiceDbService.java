package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.BorrowedService;
import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.repository.BorrowedServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowedServiceDbService implements ServiceDataBaseForBorrowed {

    private final BorrowedServiceRepository borrowedServiceRepository;

    @Override
    public BorrowedService save(BorrowedService borrowedService) {
        return borrowedServiceRepository.save(borrowedService);
    }

    @Override
    public Optional<BorrowedService> findById(Long id) {
        return borrowedServiceRepository.findById(id);
    }

    public List<BorrowedService> findAll() {
        return borrowedServiceRepository.findAll();
    }

    public List<BorrowedService> findByCopyOfBook(CopyOfBook copyOfBook) {
        return borrowedServiceRepository.findByCopyOfBook(copyOfBook);
    }
}