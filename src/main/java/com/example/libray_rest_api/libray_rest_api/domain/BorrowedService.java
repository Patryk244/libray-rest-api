package com.example.libray_rest_api.libray_rest_api.domain;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.BorrowedServiceDto;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stored_history")
public class BorrowedService {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copyId")
    private CopyOfBook copyOfBook;

    @OneToOne(fetch = FetchType.EAGER)
    private Reader reader;

    @Column(name = "borrowedDate", nullable = false)
    private LocalDate borrowedDate;

    @Column(name = "returnedDate")
    private LocalDate returnedDate;

    @Override
    public String toString() {
        return "CopyOfBook: " + copyOfBook.getCopyId() +  "Reader Id: " + reader.getId() +
                "borrowedDate: " + borrowedDate + "returnedDate: " + returnedDate;
    }

}