package fr.eni.gestionListeCourses.dal;

import fr.eni.gestionListeCourses.bo.Article;
import fr.eni.gestionListeCourses.bo.ListeCourse;

import java.sql.SQLException;
import java.util.List;

public interface ListeCourseDAO {

    public void insert(ListeCourse listeCourse) throws DALException;
    public void delete(int idListe) throws DALException;
    public List<ListeCourse> selectAll() throws DALException;
    public ListeCourse SelectById(int id) throws DALException;
    public void deleteArticle(int IdArticle) throws DALException;
    public void cocherArticle(int IdArticle) throws DALException;
    public void decocherArticle(int IdArticle) throws DALException;
}
