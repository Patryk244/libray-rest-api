package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.CopyOfBookDto;
import com.example.libray_rest_api.libray_rest_api.domain.enums.StatusCopyOfBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface AdditionalServiceControllerForBook {
    String countCopyOfBookByTitle(@PathVariable String title);
  //  ResponseEntity<CopyOfBook> changeStatusById(@PathVariable Long id, StatusCopyOfBook newStatus);
}