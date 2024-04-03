package fr.cda.bookstore.sql.entities;

import jakarta.persistence.*;

// @Entity -> Classe lié à une table dans la BDD
@Entity
// @Table -> Nom de la table à lier à l'entité
@Table(name = "book")
public class BookEntity {
    // @Id : Spécifie que l'attribut qui suit sera la clé primaire de l'objet
    @Id
    // @GeneratedValue : Auto increment (IDENTITY) géré par le SGBD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Définir le nom de la colonne à rattacher à l'attribut de la classe
    @Column(name = "id")
    private Integer id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "nb_pages")
    private Integer nbPages;

    public BookEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public void setNbPages(Integer nbPages) {
        this.nbPages = nbPages;
    }
}
