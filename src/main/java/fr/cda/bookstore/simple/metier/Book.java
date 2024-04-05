package fr.cda.bookstore.simple.metier;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public class Book {
    @Positive(message = "Le nombre de pages du livre doit être un entier positif")
    private int nbPages;
    @NotBlank(message = "Le nom du livre ne doit pas être vide")
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
