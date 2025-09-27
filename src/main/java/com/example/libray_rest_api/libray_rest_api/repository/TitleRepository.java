package com.example.libray_rest_api.libray_rest_api.repository;

import com.example.libray_rest_api.libray_rest_api.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    List<Title> findAll();
}