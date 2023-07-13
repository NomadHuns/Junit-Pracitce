package com.example.security.service;

import com.example.security.domain.BookRepository;
import com.example.security.util.MailSender;
import com.example.security.web.dto.BookResponseDTO;
import com.example.security.web.dto.BookSaveReqDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
