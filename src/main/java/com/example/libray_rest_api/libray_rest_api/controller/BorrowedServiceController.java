package com.example.libray_rest_api.libray_rest_api.controller;

import com.example.libray_rest_api.libray_rest_api.domain.BorrowedService;
import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.BorrowedServiceDto;
import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.domain.enums.StatusCopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.exception.*;
import com.example.libray_rest_api.libray_rest_api.mapper.BorrowedServiceMapper;
import com.example.libray_rest_api.libray_rest_api.service.BorrowedServiceDbService;
import com.example.libray_rest_api.libray_rest_api.service.CopyOfBookDbService;
import com.example.libray_rest_api.libray_rest_api.service.ReaderDbService;
import com.example.libray_rest_api.libray_rest_api.service.TitleDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
            if (bookIsBorrowed(wantTitle, readerId)) {
                throw new BookIsBorrowed();
            };
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

    @Override
    @PutMapping(value = "/land/book/{wantTitle}/reader/id/{readerId}")
    public ResponseEntity<BorrowedServiceDto> returnBook(@PathVariable String wantTitle,@PathVariable Long readerId) {
        if (!bookIsBorrowed(wantTitle, readerId)) {
            throw new BookIsNotLand();
        } else {
            System.out.println("Book is borrowed");
            List<BorrowedService> foundBorrowed = borrowedServiceDbService.findByReader(foundReader(readerId));
            List<CopyOfBook> copyOfBooksToUpdate = copyOfBookDbService.findByTitle(foundByTitle(wantTitle));
            Optional<BorrowedService> findActive = findActivePosition(foundBorrowed, copyOfBooksToUpdate);
            BorrowedService preparesAfterSave = updateToBorrowedService(findActive);
            return ResponseEntity.ok(borrowedServiceMapper.mapToBorrowedServiceDto(preparesAfterSave));
        }
    }

    public void bookIsInCirculationAfterBorrowed(Optional<BorrowedService> findActive) {
        BorrowedService borrowedService = findActive.get();
        CopyOfBook copyOfBook = copyOfBookDbService.findByIdFromDataBase(borrowedService.getId());
        copyOfBook.setStatusOfBook(StatusCopyOfBook.IN_CIRCULATION);
        copyOfBookDbService.saveToDataBase(copyOfBook);
    }

    public BorrowedService updateToBorrowedService(Optional<BorrowedService> findActive) {
        bookIsInCirculationAfterBorrowed(findActive);
        BorrowedService borrowedService = findActive.get();
        borrowedService.setReturnedDate(LocalDate.now());
        return borrowedServiceDbService.save(borrowedService);
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

    public boolean bookIsBorrowed(String wantTitle, Long readerId) {
        List<CopyOfBook> possibleCopy =  findBookByTitleForCopy(wantTitle);
        List<BorrowedService> readerHasBorrowedCopy = borrowedServiceDbService
                .findByReader(foundReader(readerId));
        return possibleCopy.stream()
                .anyMatch(pos -> readerHasBorrowedCopy.stream()
                        .anyMatch(has -> pos.getCopyId().equals(has.getCopyOfBook().getCopyId())));
    }

    public Optional<BorrowedService> findActivePosition(List<BorrowedService> foundBorrowed,
                                                   List<CopyOfBook> copyOfBooksToUpdate) {
        return foundBorrowed.stream()
                .filter(borrowed -> borrowed.getReturnedDate() == null)
                .filter(borrowed -> copyOfBooksToUpdate.stream()
                        .anyMatch(copy -> copy.getCopyId().equals(borrowed.getCopyOfBook().getCopyId())))
                .findFirst();
    }
}