package peaksoft;

import peaksoft.configuration.DataBaseConnection;
import peaksoft.entities.Book;
import peaksoft.services.BookService;
import peaksoft.services.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        DataBaseConnection.createEntityManagerFactory();
        BookService bookService = new BookServiceImpl();
        while (true) {
            System.out.println("press to 1 > save book");
            switch (new Scanner(System.in).nextLine()) {
                case "1" -> {
                    bookService.saveBook(new Book("Syngan kylych", "Tologon Kasymnekov", BigDecimal.valueOf(1000)));
                }
                case "2" -> {
                    bookService.saveAllBooks(List.of(
                            new Book("Kylym kaarytar bir kyn", "Chyngyz Aitmatov", BigDecimal.valueOf(1900)),
                            new Book("Samanchynyn Joly", "Chyngyz Aitmatov", BigDecimal.valueOf(1900)),
                            new Book("Baktyluuktyn formulasy", "Chubak aky Jalilov", BigDecimal.valueOf(2900)),
                            new Book("Beiysh Jolu", "Chyngyz Aitmatov", BigDecimal.valueOf(1200)),
                            new Book("Kylym kaarytar bir kyn", "Chyngyz Aitmatov", BigDecimal.valueOf(1600))

                    ));
                }
                case "3" -> {
                    bookService.findAll().forEach(System.out::println);
                }
                case "4" -> {
                    System.out.println("Write id for find :");
                    try {
                        System.out.println(bookService.findById(new Scanner(System.in).nextLong()));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "5" -> {bookService.update(1L, new Book("Toolor kylaganda", "Chyngyz Aitmatov", BigDecimal.valueOf(1200)));}
                case "6" -> {bookService.deleteBookById(2L);}
                case "7" -> {bookService.clearBook();}
                case "8" -> {bookService.deleteTable();}
            }


        }
    }
}
