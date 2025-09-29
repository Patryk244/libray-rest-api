package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.CopyOfBookDto;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.TitleDto;
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
public class CopyOfBookController implements ServiceController<CopyOfBookDto>, AdditionalServiceControllerForBook {
    @Autowired
    private CopyOfBookDbService copyOfBookDbService;

    @Autowired
    private TitleDbService titleDbService;

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private CopyOfBookMapper copyOfBookMapper;

    private TitleDto foundTitle = null;


    @Override
    @GetMapping
    public List<CopyOfBookDto> findAll() {
        return copyOfBookMapper.mapToCopyOfBookDtoList(copyOfBookDbService.findAllFromDataBase());
    }

    @GetMapping(value = "/findById/{id}")
    public CopyOfBookDto findById(@PathVariable Long id) {
        System.out.println(copyOfBookDbService.findByBookId(id));
        System.out.println("id: " + id);
        return copyOfBookMapper.mapToCopyOfBookDto(copyOfBookDbService.findByBookId(id));
    }

    @PostMapping(value = "/addByTitle/{title}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@PathVariable String title) {
        System.out.println("Searching title: " + title);
        if (titleDbService.findByTitle(title) != null) {
            CopyOfBook copyOfBook = copyOfBookMapper.mapperToCopyOfBook(new CopyOfBookDto(
                    null,
                    titleDbService.findByTitle(title).getId(),
                    StatusCopyOfBook.IN_CIRCULATION
            ));
            copyOfBookDbService.saveToDataBase(copyOfBook);
        } else {
            System.out.println("Title not found");
        }
    }

    @Override
    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        if (copyOfBookDbService.existsByIdFromDataBase(id)) {
            copyOfBookDbService.deleteByIdFromDataBase(id);
        } else {
            throw new CopyOfBookNotFound();
        }
        return ResponseEntity.ok().build();
    }


    @Override
    @GetMapping(value = "/findByTitleAndCount/{title}")
    public String countCopyOfBookByTitle(@PathVariable String title) {
        if (titleDbService.findByTitle(title) != null) {
            foundTitle = titleMapper.mapToTitleDto(titleDbService.findByTitle(title));
            return "Title: " + foundTitle.getTitle() + " has copy of book: "
                    + copyOfBookDbService.countByIdFromDataBase(foundTitle.getId()) + " quantity -> id_book: " + foundTitle.getId();
        } else {
            foundTitle = null;
            throw new TitleNotFound();
        }

    }

    @Transactional
    @PutMapping("/changeStatus/id/{id}/setStatus")
    public void changeStatusById(@PathVariable Long id, @RequestBody CopyOfBookDto statusUpdate) {
        CopyOfBook existingCopyOfBook = copyOfBookDbService.findByBookId(id);
        existingCopyOfBook.setStatusOfBook(StatusCopyOfBook.determineStatus(statusUpdate.getStatusOfBook().toString()));
       // existingCopyOfBook.setStatusOfBook(statusUpdate.getStatusOfBook());
        copyOfBookDbService.saveToDataBase(existingCopyOfBook);
    }

    public void create(CopyOfBookDto copyOfBookDto) {
    }
}