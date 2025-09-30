package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.Reader;
import com.example.libray_rest_api.libray_rest_api.repository.ReaderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService implements ServiceForDatabase <Reader> {

    private final ReaderRepository readerRepository;

    @Override
    public Reader saveToDataBase(Reader object) {
        return readerRepository.save(object);
    }

    @Override
    public void deleteAllFromDataBase() {
        readerRepository.deleteAll();
    }

    @Override
    public List<Reader> findAllFromDataBase() {
        return readerRepository.findAll();
    }

    @Transactional
    public void deleteByIdFromDataBase(final Long reader_id) {
        readerRepository.deleteById(reader_id);
    }

    public boolean existsByIdFromDataBase(final Long id) {
        return readerRepository.existsById(id);
    }


}
