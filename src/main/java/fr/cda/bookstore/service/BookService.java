package fr.cda.bookstore.service;

import fr.cda.bookstore.entities.BookEntity;
import fr.cda.bookstore.entities.repositories.BookSqlRepository.BookSqlRepository;
import fr.cda.bookstore.metier.Book;
import fr.cda.bookstore.repository.BibliothequeRepository;
import fr.cda.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

// Annotation @Service -> Définir une classe qui gère les traitements entre un controller et un repository
@Service
public class BookService {
    private BibliothequeRepository bibliothequeRepository;
    private BookSqlRepository bookSqlRepository;

    public BookService(BibliothequeRepository bibliothequeRepository, BookSqlRepository bookSqlRepository) {
        this.bibliothequeRepository = bibliothequeRepository;
        this.bookSqlRepository = bookSqlRepository;
    }

    public List<Book> getAllBooks(){
        return bibliothequeRepository.getAllBooks();
    }

    public Book getBookByTitle(String titre) throws InstanceNotFoundException {
        return bibliothequeRepository.getBookByTitle(titre);
    }

    public BookEntity getById(Integer id) throws InstanceNotFoundException {
        return bookSqlRepository.findById(id).orElseThrow(InstanceNotFoundException::new);
    }
}
