package com.example.libray_rest_api.libray_rest_api.domain.Dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long reader_id;
    private String firstname;
    private String lastname;
    private LocalDate creation_date;
}