package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.CopyOfBook;
import com.example.libray_rest_api.libray_rest_api.domain.Title;
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
    @Override
    public boolean existsByIdFromDataBase(Long id) {
        return copyOfBookRepository.existsById(id);
    }

    public CopyOfBook findByIdFromDataBase(Long id) {
        return copyOfBookRepository.findByCopyId(id);
    }

    public List<CopyOfBook> findByTitle(Title title) {
        return copyOfBookRepository.findByTitle(title);
    }
}