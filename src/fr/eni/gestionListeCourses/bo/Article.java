package fr.eni.gestionListeCourses.bo;

public class Article {
    private int id;
    private String nom;
    private boolean coche;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isCoche() {
        return coche;
    }

    public void setCoche(boolean coche) {
        this.coche = coche;
    }

    public Article() {
    }

    public Article(int id, String nom, boolean coche) {
        this.id = id;
        this.nom = nom;
        this.coche = coche;
    }

    public Article(String nom, boolean coche) {
        this.nom = nom;
        this.coche = coche;
    }

    public Article(String nom) {
        this.nom = nom;
    }
}
