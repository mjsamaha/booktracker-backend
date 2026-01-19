package com.mjsamaha.booktracker_backend.service;

import com.mjsamaha.booktracker_backend.dto.BookRequest;
import com.mjsamaha.booktracker_backend.dto.BookResponse;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse createBook(BookRequest bookRequest);

    BookResponse updateBook(Long id, BookRequest bookRequest);

    void deleteBookById(Long id);
}
