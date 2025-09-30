package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.CopyOfBookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ServiceControllerForCopyOfBook <T> {
    List<T> getAll();
    ResponseEntity<CopyOfBook> setStatusById(@PathVariable Long copyId, @RequestBody CopyOfBookDto newStatus);
    ResponseEntity<CopyOfBook> createCopyOfBook(@PathVariable String title);
}
