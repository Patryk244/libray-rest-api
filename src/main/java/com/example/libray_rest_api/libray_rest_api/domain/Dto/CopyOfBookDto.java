package com.example.libray_rest_api.libray_rest_api.domain.Dto;

import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.domain.enums.StatusCopyOfBook;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CopyOfBookDto {
    private Long CopyId;
    private Title titleId;
    private StatusCopyOfBook statusOfBook;
    @Override
    public String toString() {
        return "Id: " + CopyId + ", BookId: " + titleId + ", Status: " + statusOfBook;
    }
}