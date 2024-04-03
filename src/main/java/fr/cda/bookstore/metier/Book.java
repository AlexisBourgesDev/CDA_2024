package fr.cda.bookstore.metier;

import java.util.Objects;

public class Book {
    private int nbPages;
    private String titre;

    public Book(int nbPages) {
        this.nbPages = nbPages;
    }

    public Book(String titre, int nbPages) {
        this.nbPages = nbPages;
        this.titre = titre;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return nbPages == book.nbPages && Objects.equals(titre, book.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nbPages, titre);
    }

    @Override
    public String toString() {
        return titre;
    }
}
