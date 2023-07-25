package peaksoft.services;

import peaksoft.entities.Book;
import peaksoft.services.repositories.BookRepo;
import peaksoft.services.repositories.BookRepository;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookRepo bookRepo = new BookRepository();
    @Override
    public String saveBook(Book book) {
        bookRepo.save(book);
        return "Succesfuly saved book :" + book.toString();
    }

    @Override
    public void saveAllBooks(List<Book> books) {
        bookRepo.saveAllBooks(books);
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() ->new RuntimeException("Book with id: " + bookId + " not found"));
        return book;
    }

    @Override
    public void update(Long id, Book book) {
        bookRepo.update(id,book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepo.deleteBookById(id);
    }

    @Override
    public void clearBook() {
        bookRepo.clearBooks();
    }

    @Override
    public void deleteTable() {
        bookRepo.dropTable();
    }
}
