package com.example.libray_rest_api.libray_rest_api.domain;

import com.example.libray_rest_api.libray_rest_api.domain.enums.StatusCopyOfBook;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "copyofbooks")
public class CopyOfBook {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "book_id")
    private Long bookId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "statusOfBook")
    private StatusCopyOfBook statusOfBook;

    @Override
    public String toString() {
        return "Id: " + id + ", BookId: " + bookId + ", Status: " + statusOfBook;
    }
}