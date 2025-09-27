package com.example.libray_rest_api.libray_rest_api.mapper;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.ReaderDto;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReaderMapper {
    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getReader_id(),
                readerDto.getFirstname(),
                readerDto.getLastname(),
                readerDto.getCreation_date()
        );
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(
                reader.getReader_id(),
                reader.getFirstname(),
                reader.getLastname(),
                reader.getCreation_date()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(List<Reader> readers) {
        return readers.stream()
                .map(this::mapToReaderDto)
                .toList();
    }

}