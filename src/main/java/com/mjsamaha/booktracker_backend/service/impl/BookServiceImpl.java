package com.mjsamaha.booktracker_backend.service.impl;

import com.mjsamaha.booktracker_backend.dto.BookRequest;
import com.mjsamaha.booktracker_backend.dto.BookResponse;
import com.mjsamaha.booktracker_backend.models.Book;
import com.mjsamaha.booktracker_backend.models.enums.BookStatus;
import com.mjsamaha.booktracker_backend.repository.BookRepository;
import com.mjsamaha.booktracker_backend.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return mapToResponseDto(book);
    }

    @Override
    public BookResponse createBook(BookRequest requestDto) {
        Book book = mapToEntity(requestDto);
        Book savedBook = bookRepository.save(book);
        return mapToResponseDto(savedBook);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        updateEntityFromDto(existingBook, bookRequest);
        return mapToResponseDto(existingBook);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)){
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private BookResponse mapToResponseDto(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .status(book.getStatus())
                .notes(book.getNotes())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    private Book mapToEntity(BookRequest dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .status(dto.getStatus() != null ? dto.getStatus() : BookStatus.NOT_STARTED) // Default status
                .notes(dto.getNotes())
                .build();
    }

    private void updateEntityFromDto(Book book, BookRequest dto) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        if (dto.getStatus() != null) {
            book.setStatus(dto.getStatus());
        }
        book.setNotes(dto.getNotes());
    }
}
