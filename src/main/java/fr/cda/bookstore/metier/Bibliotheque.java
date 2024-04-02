package fr.cda.bookstore.metier;

import java.util.HashMap;
import java.util.Map;

public class Bibliotheque {
    private Map<Book, Integer> stock;

    public Bibliotheque() {
        this.stock = new HashMap<>();
    }


    public void ajoutNouveauLivre(Book book, int i) {
        this.stock.put(book, i);

        // NON BLOQUANT - MET A JOUR LA QUANTITE
        // this.stock.compute

        // BLOQUANT - IF -> throw Exception
        // Nouvelle méthode de mise a jour quantité
    }

    public boolean isDispo(Book book){
        return this.stock.get(book) > 0;
    }
}
