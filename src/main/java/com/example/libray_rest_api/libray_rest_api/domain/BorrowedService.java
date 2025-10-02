package com.example.libray_rest_api.libray_rest_api.domain;

import com.example.libray_rest_api.libray_rest_api.domain.Dto.BorrowedServiceDto;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copyId")
    private CopyOfBook copyOfBook;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "readerId")
    private Reader reader;

    @Column(name = "borrowedDate", nullable = false)
    private LocalDate borrowedDate;

    @Column(name = "returnedDate")
    private LocalDate returnedDate;

    @Override
    public String toString() {
        return "CopyId: " + id + " readerId: " + reader.getId() + " borrowedDate: " + borrowedDate + " returnedDate: " + returnedDate;
    }

}