package com.mjsamaha.booktracker_backend.dto;

import com.mjsamaha.booktracker_backend.models.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    @NotBlank(message = "Title is required")
    public String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Genre is required")
    private String genre;

    private BookStatus status;

    private String notes;
}
