package com.example.libray_rest_api.libray_rest_api.repository;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CopyOfBookRepository extends CrudRepository<CopyOfBook, Long> {
    List<CopyOfBook> findAll();
    void deleteById(Long id);
    List<CopyOfBook> findByTitle(Title title);
    CopyOfBook findByCopyId(Long id);
}