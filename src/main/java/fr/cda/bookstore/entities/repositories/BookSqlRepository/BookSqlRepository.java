package fr.cda.bookstore.entities.repositories.BookSqlRepository;

import fr.cda.bookstore.entities.BookEntity;
import fr.cda.bookstore.metier.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSqlRepository extends JpaRepository<BookEntity, Integer> {
    BookEntity findByTitreIgnoreCase(String titre);
}
