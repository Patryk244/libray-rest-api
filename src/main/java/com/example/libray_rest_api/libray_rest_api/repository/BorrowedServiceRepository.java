package com.example.libray_rest_api.libray_rest_api.repository;

import com.example.libray_rest_api.libray_rest_api.domain.BorrowedService;
import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BorrowedServiceRepository extends CrudRepository<BorrowedService, Long> {
    Optional<BorrowedService> findById(Long id);
    List<BorrowedService> findAll();
    List<BorrowedService> findByCopyOfBook(CopyOfBook copyOfBook);
    List<BorrowedService> findByReader(Reader reader);
}