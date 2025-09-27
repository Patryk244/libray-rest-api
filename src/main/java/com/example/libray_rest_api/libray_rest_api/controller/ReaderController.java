package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.ReaderDto;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import com.example.libray_rest_api.libray_rest_api.domain.exception.ReaderNotFound;
import com.example.libray_rest_api.libray_rest_api.domain.exception.ReaderNotPossibleToAdd;
import com.example.libray_rest_api.libray_rest_api.mapper.ReaderMapper;
import com.example.libray_rest_api.libray_rest_api.service.ReaderDbService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/v1/readers")
public class ReaderController {

    @Autowired
    private ReaderDbService readerDbService;

    @Autowired
    private ReaderMapper readerMapper;

    @GetMapping
    public List<ReaderDto> findAllReader() {
        return readerMapper.mapToReaderDtoList(readerDbService.findAllFromDataBase());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        try {
            Reader reader = readerMapper.mapToReader(new ReaderDto(
                    null,
                    readerDto.getFirstname(),
                    readerDto.getLastname(),
                    LocalDate.now()
            ));
            readerDbService.saveToDataBase(reader);
        } catch (Exception e) {
            throw new ReaderNotPossibleToAdd();
        }
    }

    @Transactional
    @DeleteMapping(value = "/deleteById/{reader_id}")
    public ResponseEntity<Void> deleteReaderById(@PathVariable Long reader_id) {
        if (readerDbService.existsByIdFromDataBase(reader_id)) {
            readerDbService.deleteByIdFromDataBase(reader_id);
        } else {
            throw new ReaderNotFound();
        }
        return ResponseEntity.ok().build();
    }
}