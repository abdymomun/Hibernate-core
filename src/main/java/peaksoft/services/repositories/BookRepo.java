package peaksoft.services.repositories;

import peaksoft.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo {
    void save(Book book);
    void saveAllBooks(List<Book> bookList);

    List<Book> findAll();

    Optional<Book> findById(Long bookId);

    void update(Long id, Book book);

    void deleteBookById(Long id);

    void clearBooks();

    void dropTable();
}


