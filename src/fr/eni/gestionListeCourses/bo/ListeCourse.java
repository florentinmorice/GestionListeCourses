package fr.eni.gestionListeCourses.bo;

import java.util.ArrayList;
import java.util.List;

public class ListeCourse {

    private int id;
    private String nom;
    private List<Article> listeArticles = new ArrayList<Article>();

    public ListeCourse() {

    }

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

    public List<Article> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<Article> listeArticles) {
        this.listeArticles = listeArticles;
    }

    public ListeCourse(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }


}
