package fr.cda.bookstore.sql.repositories;

import fr.cda.bookstore.sql.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;

import java.util.List;

// Créer une interface qui étend JpaRepository pour avoir accès aux méthodes de bases (CRUD)
// 2 types générique à passer à JpaRepository : Classe entité et type de la clé primaire (en général Integer ou Long)
public interface BookSqlRepository extends JpaRepository<BookEntity, Integer> {
    BookEntity findByTitreIgnoreCase(String titre);

    List<BookEntity> findByAuteurId(Integer auteurId);
}
