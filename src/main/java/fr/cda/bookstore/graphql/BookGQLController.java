package fr.cda.bookstore.graphql;

import fr.cda.bookstore.simple.metier.Book;
import fr.cda.bookstore.sql.entities.BookEntity;
import fr.cda.bookstore.sql.service.BookSqlService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Controller
public class BookGQLController {

    private BookSqlService bookSqlService;

    // Injection de dépendances via constructor
    public BookGQLController(BookSqlService bookSqlService) {
        this.bookSqlService = bookSqlService;
    }

    @QueryMapping
    public BookEntity bookById(@Argument("bookId") Integer bookId) throws InstanceNotFoundException {
        return bookSqlService.getById(bookId);
    }

    @QueryMapping
    public List<BookEntity> books() {
        return bookSqlService.getAllBooks();
    }
}