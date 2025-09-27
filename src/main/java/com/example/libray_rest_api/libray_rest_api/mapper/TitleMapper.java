package com.example.libray_rest_api.libray_rest_api.mapper;

import com.example.libray_rest_api.libray_rest_api.domain.Title;
import com.example.libray_rest_api.libray_rest_api.domain.Dto.TitleDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TitleMapper {
    public Title mapToTitle(TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getReleaseDate()
        );
    }

    public TitleDto mapToTitleDto(Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getReleaseDate()
        );
    }

    public List<TitleDto> mapToTitleDtoList(List<Title> titles) {
        return titles.stream()
                .map(this::mapToTitleDto)
                .toList();
    }
}