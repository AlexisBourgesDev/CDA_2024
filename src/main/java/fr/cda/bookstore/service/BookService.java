package fr.cda.bookstore.service;

import fr.cda.bookstore.metier.Book;
import fr.cda.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Annotation @Service -> Définir une classe qui gère les traitements entre un controller et un repository
@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }
}
