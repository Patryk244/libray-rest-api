package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.BorrowedService;
import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.BorrowedServiceDto;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.domain.enums.StatusCopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.exception.CopyOfBookNotFound;
import com.example.libray_rest_api.libray_rest_api.domain.exception.ReaderNotFound;
import com.example.libray_rest_api.libray_rest_api.domain.exception.TitleNotFound;
import com.example.libray_rest_api.libray_rest_api.mapper.BorrowedServiceMapper;
import com.example.libray_rest_api.libray_rest_api.service.BorrowedServiceDbService;
import com.example.libray_rest_api.libray_rest_api.service.CopyOfBookDbService;
import com.example.libray_rest_api.libray_rest_api.service.ReaderDbService;
import com.example.libray_rest_api.libray_rest_api.service.TitleDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@RestController
@RequestMapping("/v1/borrowedService")
public class BorrowedServiceController implements ServiceControllerBorrowed {

    @Autowired
    private BorrowedServiceDbService borrowedServiceDbService;

    @Autowired
    private CopyOfBookDbService copyOfBookDbService;

    @Autowired
    private TitleDbService titleDbService;

    @Autowired
    private ReaderDbService readerDbService;

    @Autowired
    private BorrowedServiceMapper borrowedServiceMapper;

    @Override
    @GetMapping
    public List<BorrowedServiceDto> getAll() {
        return borrowedServiceMapper.mapToBorrowedServiceDtoList(borrowedServiceDbService.findAll());
    }

    @Override
    @PostMapping(value = "/add/title/{wantTitle}/readerId/{readerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BorrowedServiceDto> borrowTitle(@PathVariable String wantTitle, @PathVariable Long readerId) {
        try {
            bookIsBorrowed(wantTitle);
            Optional<CopyOfBook> findAvailableBook = findBookByTitleForCopy(wantTitle).stream()
                    .filter(c -> c.getStatusOfBook().equals(StatusCopyOfBook.IN_CIRCULATION))
                    .findFirst();
            findAvailableBook.orElseThrow(CopyOfBookNotFound::new);
            BorrowedService toSaveBorrowed = savedBorrowedService(findAvailableBook,  readerId);
            updateToCopyOfBook(findAvailableBook);
            return ResponseEntity.ok(borrowedServiceMapper.mapToBorrowedServiceDto(toSaveBorrowed));
        } catch (CopyOfBookNotFound e) {
            throw new CopyOfBookNotFound();
        }
    }

    @PutMapping("return/copyOfBook/id/{copy}")
    public void returnBook(@PathVariable String copy) {}

    @Override
    public void returnBook(Long copyId) {

    }

    public Reader foundReader(Long readerId) {
        return readerDbService.findByIdFromDataBase(readerId).orElseThrow(ReaderNotFound::new);
    }

    public List<CopyOfBook> findBookByTitleForCopy(String wantTitle) {
        try {
            return copyOfBookDbService.findByTitle(foundByTitle(wantTitle));
        } catch (CopyOfBookNotFound e) {
            throw new CopyOfBookNotFound();
        }
    }

    public Title foundByTitle(String wantTitle) {
        Title title = titleDbService.findByTitle(wantTitle);
        if (title == null) {
            throw new TitleNotFound();
        }
        return title;
    }

    public void updateToCopyOfBook(Optional<CopyOfBook> findAvailableBook) {
        List<CopyOfBook> toSaved = findAvailableBook.stream().toList();
        toSaved.forEach(c -> c.setStatusOfBook(StatusCopyOfBook.BORROWED));
        copyOfBookDbService.saveAll(toSaved);
    }

    public BorrowedService savedBorrowedService(Optional<CopyOfBook> findAvailableBook, Long readerId) {
        CopyOfBook copyOfBook = findAvailableBook.get();
        BorrowedService BorrowedService = borrowedServiceMapper.mapToBorrowedService(
                new BorrowedServiceDto(
                        null,
                        copyOfBookDbService.findByIdFromDataBase(copyOfBook.getCopyId()),
                        foundReader(readerId),
                        LocalDate.now(),
                        null
                )
        );
        return borrowedServiceDbService.save(BorrowedService);
    }

    public void bookIsBorrowed(String wantTitle) {
        List<BorrowedService> isExist = borrowedServiceDbService.findAll();

        System.out.println("Ta ksiazka jest wypozyczona");
        System.out.println();
        List<CopyOfBook> foundCopy = findBookByTitleForCopy(wantTitle);
        for (CopyOfBook copy : foundCopy) {
            System.out.println(copy.toString());
        }

    }

}