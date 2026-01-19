package com.mjsamaha.booktracker_backend.repository;

import com.mjsamaha.booktracker_backend.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
