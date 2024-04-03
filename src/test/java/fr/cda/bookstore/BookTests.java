package fr.cda.bookstore;

import fr.cda.bookstore.metier.Bibliotheque;
import fr.cda.bookstore.metier.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookTests {

	@Test
	void countAllBooks() {
		Book book = new Book(2);
		Book book2 = new Book(4);

		Assertions.assertEquals(6, book.getNbPages() + book2.getNbPages());
	}

	@Test
	void livreIsDispo(){
		Bibliotheque biblio = new Bibliotheque();
		Book book = new Book("L1", 2);

		biblio.ajoutNouveauLivre(book, 0);
		Assertions.assertFalse(biblio.isDispo(book));
		biblio.ajoutNouveauLivre(book, 2);
		Assertions.assertTrue(biblio.isDispo(book));
	}

	@Test
	void livreIsIndispo(){
		Bibliotheque biblio = new Bibliotheque();
		Book book = new Book("L2", 2);

		biblio.ajoutNouveauLivre(book, 0);
		Assertions.assertFalse(biblio.isDispo(book));
	}



}
