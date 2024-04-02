package fr.cda.bookstore.metier;

public class Book {
    private int nbPages;

    public Book(int nbPages) {
        this.nbPages = nbPages;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }
}
