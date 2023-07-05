package com.example.security.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 등록
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

//    @BeforeAll // 테스트 시작 전에 한번만 실행
    @BeforeEach // 각 테스트 시작전에 한번씩 실행
    public void initBookData() {
        String title = "junit";
        String author = "겟인데어";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    // 책 등록
    @Test
    public void save_test() {
        // given
        String title = "junit5";
        String author = "메타코딩";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        // when
        Book bookPS = bookRepository.save(book);

        // then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 책 목록 조회
    @Test
    public void findAll_test() {
        // given
        String title = "junit";
        String author = "겟인데어";

        // when
        List<Book> bookListPS = bookRepository.findAll();

        // then
        assertEquals(title, bookListPS.get(0).getTitle());
        assertEquals(author, bookListPS.get(0).getAuthor());

    }

    // 책 한건 조회
    @Test
    public void findById_test() {
        // given
        String title = "junit";
        String author = "겟인데어";

        // when
        Book bookPS = bookRepository.findById(1L).get();

        // then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());

    }

    // 책 수정

    // 책 삭제
}
