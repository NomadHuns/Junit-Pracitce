package com.example.security.domain;

import com.example.security.web.dto.response.BookResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor @Getter @Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String author;

    @Builder
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public BookResponseDTO update(String title, String author) {
        this.title = title;
        this.author = author;
        return this.toDTO();
    }

    public BookResponseDTO toDTO() {
        return BookResponseDTO.builder()
                .id(this.id)
                .title(this.title)
                .author(this.getAuthor())
                .build();
    }
}
