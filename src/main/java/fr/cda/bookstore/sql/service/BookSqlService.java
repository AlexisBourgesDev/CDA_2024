package fr.cda.bookstore.sql.service;

import fr.cda.bookstore.sql.entities.BookEntity;
import fr.cda.bookstore.sql.repositories.BookSqlRepository;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

// Annotation @Service -> Définir une classe qui gère les traitements entre un controller et un repository
@Service
public class BookSqlService {
    private BookSqlRepository bookSqlRepository;

    public BookSqlService(BookSqlRepository bookSqlRepository) {
        this.bookSqlRepository = bookSqlRepository;
    }

    public List<BookEntity> getAllBooks(){
        return this.bookSqlRepository.findAll();
    }

    public BookEntity getBookByTitle(String titre) {
        return bookSqlRepository.findByTitreIgnoreCase(titre);
    }

    public BookEntity getById(Integer id) throws InstanceNotFoundException {
        return bookSqlRepository.findById(id).orElseThrow(InstanceNotFoundException::new);
    }
}
