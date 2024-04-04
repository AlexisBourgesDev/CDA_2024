package fr.cda.bookstore.simple.controller;

import fr.cda.bookstore.simple.metier.Book;
import fr.cda.bookstore.simple.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;

// Annotation @RestController -> Définir une classe contenant des points d'entrées
@RestController
// Permet de préfixer l'ensemble des points d'API dans cette classe
@RequestMapping("/api/books")
public class BookController {
    // Défini une dépendance BookSqlService
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

    @GetMapping("/search")
    public Book getBookByTitle(
            // @Valid -> Permet de demander à Spring d'appliquer les validations qui suivent pour ce champ, à appliquer sur chaque champ
            // Si une erreur dans une ou plusieurs validations, lève une exception géré dans ExceptionController::handleHandlerMethodValidationException
            @Valid
            // @Size -> Permet de définir une taille min/max de la chaine avec un message custom pour améliorer la lisibilité
            // On peut injecter les paramètres de l'annotation dans le message en entourant d'accolades (ex: {min})
            @Size(min = 3, message = "Le titre doit faire minimum {min} caractères")
            // Par défaut, chaque @RequestParam a un paramètre required = true
            // Si oublié lors de l'appel, il lève une exception géré dans ExceptionController::handleMissingServletRequestParameter
            @RequestParam("titre") String titre
    ) throws InstanceNotFoundException {
        return bookService.getBookByTitle(titre);
    }
}
