package com.example.libray_rest_api.libray_rest_api.service;

import com.example.libray_rest_api.libray_rest_api.domain.Title;

public interface AdditionalServiceForTitle {
    Title findByTitle(String title);
}
