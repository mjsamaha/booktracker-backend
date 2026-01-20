package com.mjsamaha.booktracker_backend;


import com.mjsamaha.booktracker_backend.models.Book;
import com.mjsamaha.booktracker_backend.models.enums.BookStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Book model class.
 * Tests the Lombok-generated builder pattern and getter methods.
 */
public class BookModelTest {

    /**
     * Test that the Book builder creates a valid object with all fields set correctly.
     * Verifies that Lombok's @Builder annotation generates working builder methods
     * and that all fields are properly assigned.
     */
    @Test
    void testBookBuilderCreatesValidObject(){
        // Given: Build a Book object using the builder pattern
        Book book = Book.builder()
                .title("The Great Gatsby")
                .author("F. Scott Fitzgerald")
                .genre("Fiction")
                .status(BookStatus.IN_PROGRESS)
                .notes("Classic American novel")
                .build();

        // Then: Verify all fields were set correctly
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("Fiction", book.getGenre());
        assertEquals(BookStatus.IN_PROGRESS, book.getStatus());
        assertEquals("Classic American novel", book.getNotes());
    }
}