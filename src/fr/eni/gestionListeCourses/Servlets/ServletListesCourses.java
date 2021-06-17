package fr.eni.gestionListeCourses.Servlets;

import fr.eni.gestionListeCourses.bll.BLLException;
import fr.eni.gestionListeCourses.bll.ListeCourseManager;
import fr.eni.gestionListeCourses.bo.ListeCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listes")
public class ServletListesCourses extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ListeCourseManager listeCourseManager = new ListeCourseManager();
        int idListCourse = 0;
        if (req.getParameter("supprimer") != null) {
            idListCourse = Integer.parseInt(req.getParameter("supprimer"));
            try {
                listeCourseManager.supprimerListe(idListCourse);
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }
        try {
            List<ListeCourse> listeListeCourse = listeCourseManager.afficherToutesLesListes();
            req.setAttribute("listeListeCourse", listeListeCourse);
            req.getRequestDispatcher("WEB-INF/jsp/listes.jsp").forward(req, resp);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
