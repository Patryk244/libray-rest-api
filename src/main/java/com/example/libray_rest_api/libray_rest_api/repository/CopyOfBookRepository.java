package com.example.libray_rest_api.libray_rest_api.repository;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CopyOfBookRepository extends CrudRepository<CopyOfBook, Long> {
    List<CopyOfBook> findAll();
    long countCopyOfBookByBookId(Long bookId);
    CopyOfBook findCopyOfBookById(Long bookId);
}