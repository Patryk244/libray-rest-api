package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.TitleDto;
import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.domain.exception.*;
import com.example.libray_rest_api.libray_rest_api.mapper.TitleMapper;
import com.example.libray_rest_api.libray_rest_api.service.TitleDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/titles")
public class TitleController {

    @Autowired
    private TitleDbService titleDbService;

    @Autowired
    private TitleMapper titleMapper;

    @GetMapping
    public List<TitleDto> findAllTitles() {
        return titleMapper.mapToTitleDtoList(titleDbService.findAllFromDataBase());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitleDto titleDto) {
        try {
            Title title = titleMapper.mapToTitle(new TitleDto(
                    null,
                    titleDto.getTitle(),
                    titleDto.getAuthor(),
                    titleDto.getReleaseDate()
            ));
            titleDbService.saveToDataBase(title);
        } catch (Exception e) {
            throw new TitleNotPossibleToAdd();
        }
    }

    @DeleteMapping(value = "/removeById/{id}")
    public ResponseEntity<Void> removeTitle(@PathVariable("id") Long id) {
        if (titleDbService.existsByIdFromDataBase(id)) {
            titleDbService.deleteByIdFromDataBase(id);
        } else {
            throw new TitleNotFound();
        }
        return ResponseEntity.ok().build();
    }
}
