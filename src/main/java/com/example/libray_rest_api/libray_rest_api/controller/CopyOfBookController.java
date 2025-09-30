package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.*;
import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.domain.enums.StatusCopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.exception.*;
import com.example.libray_rest_api.libray_rest_api.mapper.*;
import com.example.libray_rest_api.libray_rest_api.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/copyOfBooks")
public class CopyOfBookController implements ServiceControllerForCopyOfBook<CopyOfBookDto> {

    @Autowired
    private CopyOfBookDbService copyOfBookDbService;

    @Autowired
    private TitleDbService titleDbService;

    @Autowired
    private CopyOfBookMapper copyOfBookMapper;

    @Override
    @GetMapping
    public List<CopyOfBookDto> getAll() {
        return copyOfBookMapper.mapToCopyOfBookDtoList(copyOfBookDbService.findAllFromDataBase());
    }

    @GetMapping(value = "/countTitleBooks/{title}")
    public String countTitleBooksByTitle(@PathVariable String title) {
        Title foundId = titleDbService.findByTitle(title);
        if (foundId == null) {
            throw new TitleNotFound();
        }
        List<CopyOfBook> foundBooks = copyOfBookDbService.findByTitle(foundId);
        int size = foundBooks.size();
        return String.format("The number of copies of the title: %s is: %d", title, size);
    }

    @PostMapping(value = "/addByTitle/{title}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyOfBook> createCopyOfBook(@PathVariable String title) {
        Title found = titleDbService.findByTitle(title);
        CopyOfBook copyOfBook = copyOfBookMapper.mapperToCopyOfBook(new CopyOfBookDto(
                null,
                found,
                StatusCopyOfBook.IN_CIRCULATION
        ));
        copyOfBookDbService.saveToDataBase(copyOfBook);
        return ResponseEntity.ok(copyOfBook);
    }
    
    @Override
    @PutMapping(value = "/setStatusById/{copyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyOfBook> setStatusById(@PathVariable Long copyId, @RequestBody CopyOfBookDto newStatus) {
        CopyOfBook findingCopy = copyOfBookDbService.findByIdFromDataBase(copyId);
        findingCopy.setStatusOfBook(newStatus.getStatusOfBook());
        copyOfBookDbService.saveToDataBase(findingCopy);
        return ResponseEntity.ok(findingCopy);
    }
}