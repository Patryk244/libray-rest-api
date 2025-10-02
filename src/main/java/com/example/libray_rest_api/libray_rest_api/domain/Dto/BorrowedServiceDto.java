package com.example.libray_rest_api.libray_rest_api.domain.Dto;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowedServiceDto {
    private Long id;
    private CopyOfBook copyOfBook;
    private Reader reader;
    private LocalDate borrowedDate;
    private LocalDate returnedDate;
}