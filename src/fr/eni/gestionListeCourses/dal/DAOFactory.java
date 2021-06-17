package fr.eni.gestionListeCourses.dal;

public class DAOFactory {

    public static ListeCourseDAO getListeCourseDAO(){
        return new ListeCourseDAOJdbcImpl();
    }
}
