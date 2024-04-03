package fr.cda.bookstore.simple.service;

import fr.cda.bookstore.simple.metier.Book;
import fr.cda.bookstore.simple.repository.BibliothequeRepository;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

// Annotation @Service -> Définir une classe qui gère les traitements entre un controller et un repository
@Service
public class BookService {
    private BibliothequeRepository bibliothequeRepository;

    public BookService(BibliothequeRepository bibliothequeRepository) {
        this.bibliothequeRepository = bibliothequeRepository;
    }

    public List<Book> getAllBooks(){
        return bibliothequeRepository.getAllBooks();
    }

    public Book getBookByTitle(String titre) throws InstanceNotFoundException {
        return bibliothequeRepository.getBookByTitle(titre);
    }
}
