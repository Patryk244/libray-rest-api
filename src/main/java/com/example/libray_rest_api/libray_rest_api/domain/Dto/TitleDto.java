package com.example.libray_rest_api.libray_rest_api.domain.Dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class TitleDto {
    private Long title_id;
    private String title;
    private String author;
    private String releaseDate;
}