package fr.cda.bookstore.simple.repository;

import fr.cda.bookstore.simple.metier.Book;
import fr.cda.bookstore.sql.entities.BookEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

// Annotation @Repository -> Définir une classe contenant des accès aux données
@Repository
public class BookRepository {
    private List<BookEntity> books;

    public BookRepository() {
        books = new ArrayList<>();
        books.add(new BookEntity("L1", 2));
    }

    public List<BookEntity> getAllBooks(){
        return books;
    }

    public BookEntity getBookByTitle(String titre) {
        return books.stream().filter(book -> book.getTitre().equals(titre)).findFirst().orElse(null);
    }

    public Flux<BookEntity> findByTitle(String title) {
        return Flux.fromIterable(books);
    }

    public BookEntity add(BookEntity book){
        books.add(book);
        return book;
    }
}
