package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.CopyOfBookDto;
import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.repository.CopyOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyOfBookDbService implements ServiceForDatabase <CopyOfBook> {

    private final CopyOfBookRepository copyOfBookRepository;

    @Override
    public CopyOfBook saveToDataBase(CopyOfBook object) {
        return copyOfBookRepository.save(object);
    }

    @Override
    public void deleteAllFromDataBase() {
        copyOfBookRepository.deleteAll();
    }

    @Override
    public List<CopyOfBook> findAllFromDataBase() {
        return copyOfBookRepository.findAll();
    }

    @Override
    public void deleteByIdFromDataBase(Long id) {
        copyOfBookRepository.deleteById(id);
    }

    public CopyOfBook findByBookId(Long bookId) {
        return copyOfBookRepository.findCopyOfBookById(bookId);
    }

    @Override
    public boolean existsByIdFromDataBase(Long id) {
        return copyOfBookRepository.existsById(id);
    }

    public long countByIdFromDataBase(Long id) {
        return copyOfBookRepository.countCopyOfBookByBookId(id);
    }
}