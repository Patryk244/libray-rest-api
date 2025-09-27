package com.example.libray_rest_api.libray_rest_api.repository;


import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Integer> {
    List<Reader> findAll();
    void deleteById(@PathVariable Long id);
}