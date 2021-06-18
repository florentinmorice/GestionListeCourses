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

@WebServlet("/nouvelle")
public class ServletNouvelleListe extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ListeCourseManager listeCourseManager = new ListeCourseManager();
        if (req.getServletPath().equals("/nouvelle")) {
            int idListeCourse = 0;
            try {
                if (req.getParameter("id") != null) {
                    idListeCourse = Integer.parseInt(req.getParameter("id"));
                    ListeCourse listeCourse = listeCourseManager.afficherUneListe(idListeCourse);
                    req.setAttribute("listeCourse", listeCourse);
                }

            } catch (BLLException e) {
                e.printStackTrace();
            }
        }
            int id_article = 0;
            int id_liste = 0;
            if(req.getParameter("id_article") != null){
                id_article = Integer.parseInt(req.getParameter("id_article"));
                id_liste = Integer.parseInt(req.getParameter("id_liste"));

                try {
                    listeCourseManager.supprimerArticle(id_article);
                    ListeCourse listeCourse = listeCourseManager.afficherUneListe(id_liste);
                    req.setAttribute("listeCourse", listeCourse);
                } catch (BLLException bllException) {
                    bllException.printStackTrace();
                }
            }
        req.getRequestDispatcher("WEB-INF/jsp/nouvelleListe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListeCourseManager listeCourseManager = new ListeCourseManager();
        //Je récupère les noms de la liste et le nom de l'article
        String nom_article = req.getParameter("nom_article");
        ListeCourse listeCourse = null;

        try {
            // Si la liste existe déjà (id!=null)
            if (req.getParameter("id") != null) {
                // On récupère les articles associés à la liste
                int idListeCourse = Integer.parseInt(req.getParameter("id"));
                String nomArticle = req.getParameter("nom_article");
                // On ajoute un article à la liste existante
                listeCourseManager.ajouterArticle(idListeCourse, nomArticle);
                listeCourse = listeCourseManager.afficherUneListe(idListeCourse);
            } else {

                // On récupère les articles associés à la liste
                // On ajoute un article à la liste existante
                //Si la liste n'exite pas
                // On ajoute une liste et son premier article
                String nom_liste = req.getParameter("nom_liste");
                listeCourse = listeCourseManager.ajouterListeEtArticle(nom_liste, nom_article);
                listeCourse = listeCourseManager.afficherUneListe(listeCourse.getId());
            }


            //On passe la liste de course à jour à la jsp
            req.setAttribute("listeCourse", listeCourse);
            req.getRequestDispatcher("WEB-INF/jsp/nouvelleListe.jsp").forward(req, resp);
            System.out.println("Nom de la liste  : " + listeCourse.getNom()
                    + " Nom l'ID : " + listeCourse.getId());


            //
        } catch (BLLException e) {
            e.printStackTrace();
        }


    }
}
