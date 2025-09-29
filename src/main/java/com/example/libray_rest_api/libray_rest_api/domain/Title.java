package com.example.libray_rest_api.libray_rest_api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 80, message = "Title must contains between 4 and 80 characters.")
    @Column(name = "title", unique = true)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 25, message = "Title author contains between 4 and 25 characters.")
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "releaseDate")
    private int releaseDate;
}