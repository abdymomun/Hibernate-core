package peaksoft.services;

import peaksoft.entities.Book;

import java.util.List;

public interface BookService {
    String saveBook(Book book);

    void saveAllBooks(List<Book> books);

    List<Book> findAll();

    Book findById(Long bookId);

    void update(Long id,Book book);

    void deleteBookById(Long id);

    void clearBook();

    void deleteTable();
}

