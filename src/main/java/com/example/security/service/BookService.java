package com.example.security.service;

import com.example.security.domain.Book;
import com.example.security.domain.BookRepository;
import com.example.security.web.dto.BookResponseDTO;
import com.example.security.web.dto.BookSaveReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDTO 책등록하기(BookSaveReqDTO requestDTO){
        Book bookPS = bookRepository.save(requestDTO.toEntity());
        return new BookResponseDTO().toDTO(bookPS);
    };

    // 책 목록 조회
    public List<BookResponseDTO> 책목록보기() {
        return bookRepository.findAll().stream()
                .map(new BookResponseDTO()::toDTO)
                .collect(Collectors.toList());
    }

    // 책 한건 조회

    // 책 삭제

    // 책 수정
}
