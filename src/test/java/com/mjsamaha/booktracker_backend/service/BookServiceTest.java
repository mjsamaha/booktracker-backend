package com.mjsamaha.booktracker_backend.service;

import com.mjsamaha.booktracker_backend.dto.BookRequest;
import com.mjsamaha.booktracker_backend.dto.BookResponse;
import com.mjsamaha.booktracker_backend.models.Book;
import com.mjsamaha.booktracker_backend.models.enums.BookStatus;
import com.mjsamaha.booktracker_backend.repository.BookRepository;
import com.mjsamaha.booktracker_backend.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookServiceImpl bookService;

    private Book book;
    private BookRequest bookRequest;
    private BookResponse bookResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);

        book = Book.builder()
                .id(1L)
                .title("Test Title")
                .author("Test Author")
                .genre("Fiction")
                .status(BookStatus.IN_PROGRESS)
                .notes("Test Notes")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        bookRequest = BookRequest.builder()
                .title("Test Title")
                .author("Test Author")
                .genre("Fiction")
                .status(BookStatus.IN_PROGRESS)
                .notes("Test Notes")
                .build();

        bookResponse = BookResponse.builder()
                .id(1L)
                .title("Test Title")
                .author("Test Author")
                .genre("Fiction")
                .status(BookStatus.IN_PROGRESS)
                .notes("Test Notes")
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Test
    void getAllBooks_shouldReturnListOfBookResponses() {
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<BookResponse> result = bookService.getAllBooks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Title");
        verify(bookRepository).findAll();
    }

    @Test
    void getBookById_shouldReturnBookResponse_whenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponse result = bookService.getBookById(1L);

        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(bookRepository).findById(1L);
    }

    @Test
    void getBookById_shouldThrowException_whenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getBookById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Book not found with id: 1");
    }

    @Test
    void createBook_shouldReturnBookResponse() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookResponse result = bookService.createBook(bookRequest);

        assertThat(result.getTitle()).isEqualTo("Test Title");
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void updateBook_shouldReturnUpdatedBookResponse_whenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookRequest updateRequest = BookRequest.builder()
                .title("Updated Title")
                .author("Test Author")
                .genre("Fiction")
                .status(BookStatus.COMPLETED)
                .notes("Updated Notes")
                .build();

        BookResponse result = bookService.updateBook(1L, updateRequest);

        assertThat(result.getTitle()).isEqualTo("Updated Title");
        assertThat(result.getStatus()).isEqualTo(BookStatus.COMPLETED);
        verify(bookRepository).findById(1L);
    }

    @Test
    void updateBook_shouldThrowException_whenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.updateBook(1L, bookRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Book not found with id: 1");
    }

    @Test
    void deleteBookById_shouldDelete_whenBookExists() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        bookService.deleteBookById(1L);

        verify(bookRepository).deleteById(1L);
    }

    @Test
    void deleteBookById_shouldThrowException_whenBookNotFound() {
        when(bookRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> bookService.deleteBookById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Book not found with id: 1");
    }
}
