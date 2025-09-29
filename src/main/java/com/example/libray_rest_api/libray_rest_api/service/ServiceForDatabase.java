package com.example.libray_rest_api.libray_rest_api.service;
import java.util.*;

public interface ServiceForDatabase <T> {
    T saveToDataBase(T object);
    void deleteAllFromDataBase();
    List<T> findAllFromDataBase();
    void deleteByIdFromDataBase(final Long id);
    boolean existsByIdFromDataBase(final Long id);
}