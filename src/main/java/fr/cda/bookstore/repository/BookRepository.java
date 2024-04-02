package fr.cda.bookstore.repository;

import fr.cda.bookstore.metier.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Annotation @Repository -> Définir une classe contenant des accès aux données
@Repository
public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        books = new ArrayList<>();
        books.add(new Book("L1", 2));
    }

    public List<Book> getAllBooks(){
        return books;
    }
}
