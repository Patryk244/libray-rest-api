package com.example.libray_rest_api.libray_rest_api.mapper;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.CopyOfBookDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CopyOfBookMapper {

    public CopyOfBook mapperToCopyOfBook(CopyOfBookDto copyOfBookDto) {
        return new CopyOfBook(
                copyOfBookDto.getId(),
                copyOfBookDto.getBookId(),
                copyOfBookDto.getStatusOfBook()
        );
    }

    public CopyOfBookDto mapToCopyOfBookDto(CopyOfBook copyOfBook) {
        return new CopyOfBookDto(
                copyOfBook.getId(),
                copyOfBook.getBookId(),
                copyOfBook.getStatusOfBook()
        );
    }

    public List<CopyOfBookDto> mapToCopyOfBookDtoList(List<CopyOfBook> copyOfBooks) {
        return copyOfBooks.stream()
                .map(this::mapToCopyOfBookDto)
                .toList();
    }
}