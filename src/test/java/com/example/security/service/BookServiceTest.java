package com.example.security.service;

import com.example.security.domain.Book;
import com.example.security.domain.BookRepository;
import com.example.security.util.MailSender;
import com.example.security.web.dto.BookResponseDTO;
import com.example.security.web.dto.BookSaveReqDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // 가짜 객체 보관 환경(Mockito) 추가
public class BookServiceTest {

    @InjectMocks // 의존성이 걸려있는 가짜 객체들을 주입시켜준다.
    private BookService bookService;

    @Mock // Mockito에 가짜 객체 추가
    private BookRepository bookRepository;

    @Mock // Mockito에 가짜 객체 추가
    private MailSender mailSender;

    @Test
    public void 책등록하기_test() {
        // given
        BookSaveReqDTO requestDTO = new BookSaveReqDTO();
        requestDTO.setTitie("Junit5");
        requestDTO.setAuthor("metacoding");

        // stub
        when(bookRepository.save(any())).thenReturn(requestDTO.toEntity());
        when(mailSender.send()).thenReturn(true);

        // when
        BookResponseDTO responseDTO = bookService.책등록하기(requestDTO);

        // then
        assertThat(requestDTO.getTitie()).isEqualTo(responseDTO.getTitle());
        assertThat(requestDTO.getAuthor()).isEqualTo(responseDTO.getAuthor());
    }

    @Test
    public void 책목록보기_test() {
        // given

        // stub
        // Book 엔티티를 담을 리스트 생성
        List<Book> bookList = new ArrayList<>();

        // 리스트에 객체 생성하여 담기
        bookList.add(new Book(1L, "junit5", "메타코딩"));
        bookList.add(new Book(2L, "Spring 강의", "메타코딩"));

        // bookRepository.findAll() 메서드 호출 시 리턴될 객체 정의
        when(bookRepository.findAll()).thenReturn(bookList);

        // when
        // 테스트할 서비스 호출
        List<BookResponseDTO> bookResponseDTOList = bookService.책목록보기();

        // then
        // Book의 title 비교
        assertThat(bookList.get(0).getTitle()).isEqualTo(bookResponseDTOList.get(0).getTitle());
        assertThat(bookList.get(1).getTitle()).isEqualTo(bookResponseDTOList.get(1).getTitle());

        // Book의 author 비교
        assertThat(bookList.get(0).getAuthor()).isEqualTo(bookResponseDTOList.get(0).getAuthor());
        assertThat(bookList.get(1).getAuthor()).isEqualTo(bookResponseDTOList.get(1).getAuthor());

    }
}
