package com.example.libray_rest_api.libray_rest_api.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface ServiceController<T> {
    List<T> findAll();
    void create(T dto);
    ResponseEntity<Void> remove(Long id);
}