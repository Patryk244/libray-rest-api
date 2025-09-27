package com.example.libray_rest_api.libray_rest_api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 15, message = "Your firstname must contains from 3 to 15 characters.")
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 15, message = "Your lastname must contains from 3 to 15 characters.")
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @NotBlank
    @FutureOrPresent(message = "Time must be current or next. Never be previous")
    @Column(name = "creation_date")
    private LocalDate creation_date;
}
