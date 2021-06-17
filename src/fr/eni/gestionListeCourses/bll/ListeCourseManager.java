package fr.eni.gestionListeCourses.bll;

import fr.eni.gestionListeCourses.bo.Article;
import fr.eni.gestionListeCourses.bo.ListeCourse;
import fr.eni.gestionListeCourses.dal.DALException;
import fr.eni.gestionListeCourses.dal.DAOFactory;
import fr.eni.gestionListeCourses.dal.ListeCourseDAO;

import java.util.List;

public class ListeCourseManager {

    ListeCourseDAO listeCourseDAO;

    public ListeCourseManager(){
        listeCourseDAO = DAOFactory.getListeCourseDAO();
    }

    public ListeCourse ajouterListeEtArticle(String nom_liste, String nom_article) throws BLLException {
        ListeCourse listeCourse = new ListeCourse();
        listeCourse.setNom(nom_liste);
        Article article = new Article();
        article.setNom(nom_article);
        listeCourse.getListeArticles().add(article);
        try {
            this.listeCourseDAO.insert(listeCourse);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur lors de l'insertion");
        }

        return listeCourse;
    }

    public void supprimerListe(int idListe) throws BLLException {

        try {
            this.listeCourseDAO.delete(idListe);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la suppression de cette liste");
        }

    }

    public void supprimerArticle(int idArticle) throws BLLException {

        try {
            this.listeCourseDAO.deleteArticle(idArticle);
        } catch (DALException e) {
            throw new BLLException("Erreur lors de la suppression de l'article");
        }

    }

    public List<ListeCourse> afficherToutesLesListes() throws BLLException {

        try {
            return listeCourseDAO.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur d'affichage des listes de courses");
        }
    }

    public ListeCourse afficherUneListe(int idListe) throws BLLException {

        try {
            return this.listeCourseDAO.SelectById(idListe);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur d'affichage de la liste");

        }

    }

    public void ajouterArticle (int idListe, String nomArticle) throws BLLException {
        ListeCourse listeCourse = new ListeCourse();
        listeCourse.setId(idListe);
        Article article = new Article(nomArticle);
        listeCourse.getListeArticles().add(article);
        try {
            this.listeCourseDAO.insert(listeCourse);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur lors de l'ajout de l'article");
        }


    }


    public void cocherArticle (int idArticle) throws BLLException {

        try {
            this.listeCourseDAO.cocherArticle(idArticle);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur lors du cochage de l'article");
        }

    }

    public void decocherArticle (int idArticle) throws BLLException {

        try {
            this.listeCourseDAO.decocherArticle(idArticle);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur lors du cochage de l'article");
        }

    }

}
