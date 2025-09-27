package com.example.libray_rest_api.libray_rest_api.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "titles")
public class Title {
    @Id
    @GeneratedValue
    private Long title_id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "releaseDate")
    private String releaseDate;
}