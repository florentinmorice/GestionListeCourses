package fr.eni.gestionListeCourses.Servlets;

import fr.eni.gestionListeCourses.bll.BLLException;
import fr.eni.gestionListeCourses.bll.ListeCourseManager;
import fr.eni.gestionListeCourses.bo.ListeCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/panier")
public class ServletPanier extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ListeCourseManager listeCourseManager = new ListeCourseManager();
        int idListCourse = 0;
        ListeCourse listeCourse = null;
        if (req.getServletPath().equals("/panier")) {
            if (req.getParameter("id") != null) {
                idListCourse = Integer.parseInt(req.getParameter("id"));
                try {
                    listeCourse = listeCourseManager.afficherUneListe(idListCourse);
                    req.setAttribute("listeCourse", listeCourse);
                } catch (BLLException e) {
                    e.printStackTrace();
                }
            }
        }
        req.getRequestDispatcher("WEB-INF/jsp/panier.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListeCourseManager listeCourseManager = new ListeCourseManager();
        int idArticle = Integer.parseInt(req.getParameter("id_article"));
        int idListe = Integer.parseInt(req.getParameter("id_liste"));
        boolean coche = Boolean.parseBoolean(req.getParameter("coche"));
        try {
            if (req.getParameter("coche") != null) {
                listeCourseManager.cocherArticle(idArticle);
            } else {
                listeCourseManager.decocherArticle(idArticle);
            }

        } catch (BLLException bllException){
            bllException.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/jsp/panier.jsp").forward(req, resp);
    }
}
