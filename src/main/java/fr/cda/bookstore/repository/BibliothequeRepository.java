package fr.cda.bookstore.repository;

import fr.cda.bookstore.metier.Bibliotheque;
import fr.cda.bookstore.metier.Book;
import org.springframework.stereotype.Repository;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Repository
public class BibliothequeRepository {
    private Bibliotheque bibliotheque;

    public BibliothequeRepository() {
        bibliotheque = new Bibliotheque();
        Book book = new Book("L1", 2);
        bibliotheque.ajoutNouveauLivre(book, 0);
        Book book2 = new Book("L2", 10);
        bibliotheque.ajoutNouveauLivre(book2, 0);
    }

    public void ajouterNouveauLivre(Book b, int exemplaires){
        bibliotheque.ajoutNouveauLivre(b, exemplaires);
    }

    public List<Book> getAllBooks(){
        return bibliotheque.getAllBooksDispo();
    }

    public Book getBookByTitle(String titre) throws InstanceNotFoundException {
        return bibliotheque.rechercheParTitre(titre);
    }
}
