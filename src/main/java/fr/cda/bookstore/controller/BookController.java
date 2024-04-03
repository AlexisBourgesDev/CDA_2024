package fr.cda.bookstore.controller;

import fr.cda.bookstore.entities.BookEntity;
import fr.cda.bookstore.metier.Book;
import fr.cda.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Annotation @RestController -> Définir une classe contenant des points d'entrées
@RestController
// Permet de préfixer l'ensemble des points d'API dans cette classe
@RequestMapping("/api/books")
public class BookController {
    // Défini une dépendance BookService
    private BookService bookService;

    // Injection de dépendances via constructor
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Annotation @GetMapping -> Définir un point d'API GET (en paramètre, le chemin de la ressource, A CONCATENER AVEC LE REQUEST MAPPING)
    @GetMapping("")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    // Annotation @GetMapping -> Définir un point d'API GET (en paramètre, le chemin de la ressource, A CONCATENER AVEC LE REQUEST MAPPING)
    @GetMapping("/{id}")
    public BookEntity getAllBooks(@PathVariable("id") Integer id) throws InstanceNotFoundException {
        return bookService.getById(id);
    }

    @GetMapping("/search")
    public Book getBookByTitle(@RequestParam("titre") String titre) throws InstanceNotFoundException {
        return bookService.getBookByTitle(titre);
    }
}
