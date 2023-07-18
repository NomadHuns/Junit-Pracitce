package com.example.security.web.dto.response;

import com.example.security.domain.Book;
import lombok.*;

@Getter @NoArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;

    @Builder
    public BookResponseDTO(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
