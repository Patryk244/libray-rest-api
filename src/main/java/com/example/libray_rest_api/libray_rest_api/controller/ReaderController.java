package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.ReaderDto;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import com.example.libray_rest_api.libray_rest_api.mapper.ReaderMapper;
import com.example.libray_rest_api.libray_rest_api.service.ReaderDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(new ReaderDto(
                null,
                readerDto.getFirstname(),
                readerDto.getLastname(),
                null
        ));
        readerDbService.saveToDataBase(reader);
    }
}