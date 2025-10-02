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
    private Long copyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "titleId")
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "statusOfBook")
    private StatusCopyOfBook statusOfBook;

    @Override
    public String toString() {
        return "CopyID: " + copyId + " Status: " + statusOfBook + " TitleId: "  + title.getId();
    }
}