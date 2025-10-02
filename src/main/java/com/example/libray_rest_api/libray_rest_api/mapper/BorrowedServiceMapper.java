package com.example.libray_rest_api.libray_rest_api.mapper;

import com.example.libray_rest_api.libray_rest_api.domain.BorrowedService;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.BorrowedServiceDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BorrowedServiceMapper {

    public BorrowedService mapToBorrowedService(BorrowedServiceDto borrowedServiceDto) {
        return new BorrowedService(
                borrowedServiceDto.getId(),
                borrowedServiceDto.getCopyOfBook(),
                borrowedServiceDto.getReader(),
                borrowedServiceDto.getBorrowedDate(),
                borrowedServiceDto.getReturnedDate()
        );
    }

    public BorrowedServiceDto mapToBorrowedServiceDto(BorrowedService borrowedService) {
        return new BorrowedServiceDto(
                borrowedService.getId(),
                borrowedService.getCopyOfBook(),
                borrowedService.getReader(),
                borrowedService.getBorrowedDate(),
                borrowedService.getReturnedDate()
        );
    }

    public List<BorrowedServiceDto> mapToBorrowedServiceDtoList(List<BorrowedService> borrowedServices) {
        return borrowedServices.stream()
                .map(this::mapToBorrowedServiceDto)
                .toList();
    }
}