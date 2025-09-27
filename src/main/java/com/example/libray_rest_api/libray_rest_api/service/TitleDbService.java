package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.repository.TitleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService implements ServiceForDatabase <Title> {

    private final TitleRepository titleRepository;

    public Title saveToDataBase(Title object) {
        return titleRepository.save(object);
    }

    @Override
    public void deleteAllFromDataBase() {
        titleRepository.deleteAll();
    }

    @Override
    public List<Title> findAllFromDataBase() {
        return titleRepository.findAll();
    }

    @Transactional
    public void deleteByIdFromDataBase(Long id) {
        titleRepository.deleteById(id);
    }

    @Override
    public boolean existsByIdFromDataBase(Long id) {
        return titleRepository.existsById(id);
    }
}
