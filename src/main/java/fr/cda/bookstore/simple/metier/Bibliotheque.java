package fr.cda.bookstore.simple.metier;

import javax.management.InstanceNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Bibliotheque {
    private Map<Book, Integer> stock;

    public Bibliotheque() {
        this.stock = new HashMap<>();
    }


    public void ajoutNouveauLivre(Book book, int qte) {
        this.stock.put(book, qte);

        // NON BLOQUANT - MET A JOUR LA QUANTITE
        // this.stock.compute(book, (b,quantite) -> Optional.ofNullable(quantite).orElse(0) + qte);

        // BLOQUANT - IF -> throw Exception
        // if (this.stock.containsKey(book){
        //      throw new KeyAlreadyExistsException();
        // }
        // Nouvelle méthode de mise a jour quantité
    }

    public void ajoutExemplaireLivre(Book b, int qte) throws InstanceNotFoundException {
        if (this.stock.containsKey(b)){
            Integer nbExemplaires = this.stock.get(b);
            this.stock.put(b, nbExemplaires + qte);
        } else {
            throw new InstanceNotFoundException();
        }
    }

    public boolean isDispo(Book book){
        return this.stock.get(book) > 0;
    }

    public Book rechercheParTitre(String titre) throws InstanceNotFoundException {
        return this.stock.keySet()
                .stream()
                .filter(book -> book.getTitre().equals(titre))
                .findFirst()
                .orElseThrow(InstanceNotFoundException::new);
    }

    public Integer getNbExemplairesForBook(Book b) throws InstanceNotFoundException {
        return Optional.ofNullable(this.stock.get(b)).orElseThrow(InstanceNotFoundException::new);
    }

    public List<Book> getAllBooksDispo(){
        return this.stock.entrySet().stream().filter(book -> book.getValue() > 0).map(Map.Entry::getKey).toList();
    }
}
