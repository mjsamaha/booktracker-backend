package com.mjsamaha.booktracker_backend.config;

import com.mjsamaha.booktracker_backend.models.Book;
import com.mjsamaha.booktracker_backend.models.enums.BookStatus;
import com.mjsamaha.booktracker_backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            // Seed sample data
            Book book1 = Book.builder()
                    .title("The Great Gatsby")
                    .author("F. Scott Fitzgerald")
                    .genre("Fiction")
                    .status(BookStatus.NOT_STARTED)
                    .notes("A classic American novel about the Jazz Age.")
                    .build();

            Book book2 = Book.builder()
                    .title("1984")
                    .author("George Orwell")
                    .genre("Dystopian")
                    .status(BookStatus.IN_PROGRESS)
                    .notes("A dystopian novel about totalitarianism.")
                    .build();

            Book book3 = Book.builder()
                    .title("To Kill a Mockingbird")
                    .author("Harper Lee")
                    .genre("Fiction")
                    .status(BookStatus.NOT_STARTED)
                    .notes("A novel about racial injustice in the American South.")
                    .build();

            Book book4 = Book.builder()
                    .title("Pride and Prejudice")
                    .author("Jane Austen")
                    .genre("Romance")
                    .status(BookStatus.COMPLETED)
                    .notes("A romantic novel about manners and marriage.")
                    .build();

            Book book5 = Book.builder()
                    .title("The Hobbit")
                    .author("J.R.R. Tolkien")
                    .genre("Fantasy")
                    .status(BookStatus.COMPLETED)
                    .notes("A fantasy adventure about a hobbit's unexpected journey.")
                    .build();

            Book book6 = Book.builder()
                    .title("Sapiens")
                    .author("Yuval Noah Harari")
                    .genre("Non-Fiction")
                    .status(BookStatus.IN_PROGRESS)
                    .notes("A brief history of humankind from the Stone Age to modern day.")
                    .build();

            Book book7 = Book.builder()
                    .title("The Catcher in the Rye")
                    .author("J.D. Salinger")
                    .genre("Fiction")
                    .status(BookStatus.COMPLETED)
                    .notes("A coming-of-age story about teenage rebellion and alienation.")
                    .build();

            Book book8 = Book.builder()
                    .title("Dune")
                    .author("Frank Herbert")
                    .genre("Science Fiction")
                    .status(BookStatus.NOT_STARTED)
                    .notes("An epic sci-fi novel set on the desert planet Arrakis.")
                    .build();

            Book book9 = Book.builder()
                    .title("The Alchemist")
                    .author("Paulo Coelho")
                    .genre("Fiction")
                    .status(BookStatus.COMPLETED)
                    .notes("A philosophical tale about following your dreams.")
                    .build();

            Book book10 = Book.builder()
                    .title("Educated")
                    .author("Tara Westover")
                    .genre("Memoir")
                    .status(BookStatus.IN_PROGRESS)
                    .notes("A memoir about growing up in a survivalist family and pursuing education.")
                    .build();

            Book book11 = Book.builder()
                    .title("The Shining")
                    .author("Stephen King")
                    .genre("Horror")
                    .status(BookStatus.NOT_STARTED)
                    .notes("A horror novel about a family isolated in a haunted hotel.")
                    .build();

            Book book12 = Book.builder()
                    .title("Becoming")
                    .author("Michelle Obama")
                    .genre("Biography")
                    .status(BookStatus.COMPLETED)
                    .notes("A memoir by the former First Lady of the United States.")
                    .build();

            Book book13 = Book.builder()
                    .title("Harry Potter and the Sorcerer's Stone")
                    .author("J.K. Rowling")
                    .genre("Fantasy")
                    .status(BookStatus.COMPLETED)
                    .notes("The first book in the beloved Harry Potter series.")
                    .build();

            Book book14 = Book.builder()
                    .title("Atomic Habits")
                    .author("James Clear")
                    .genre("Self-Help")
                    .status(BookStatus.IN_PROGRESS)
                    .notes("A practical guide to building good habits and breaking bad ones.")
                    .build();

            Book book15 = Book.builder()
                    .title("The Midnight Library")
                    .author("Matt Haig")
                    .genre("Fiction")
                    .status(BookStatus.NOT_STARTED)
                    .notes("A novel about infinite possibilities and second chances.")
                    .build();

            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);
            bookRepository.save(book4);
            bookRepository.save(book5);
            bookRepository.save(book6);
            bookRepository.save(book7);
            bookRepository.save(book8);
            bookRepository.save(book9);
            bookRepository.save(book10);
            bookRepository.save(book11);
            bookRepository.save(book12);
            bookRepository.save(book13);
            bookRepository.save(book14);
            bookRepository.save(book15);

            System.out.println("Sample books loaded into the database.");
        }
    }
}
