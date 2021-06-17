package fr.eni.gestionListeCourses.dal;

import fr.eni.gestionListeCourses.bll.BLLException;
import fr.eni.gestionListeCourses.bo.Article;
import fr.eni.gestionListeCourses.bo.ListeCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListeCourseDAOJdbcImpl implements ListeCourseDAO {

    private static final String SELECT_ALL = "SELECT id, nom FROM LISTES;";
    private static final String SELECT_BY_ID = "SELECT " +
            "	l.id AS id_liste, " +
            "	l.nom AS nom_liste, " +
            "	a.id AS id_article, " +
            "	a.nom AS nom_article, " +
            "	a.coche " +
            "FROM listes l LEFT JOIN articles a ON l.id=a.id_liste " +
            "WHERE l.id=?";

    private static final String INSERT_LISTE = "INSERT INTO LISTES(nom) values(?);";
    private static final String DELETE_LISTE = "DELETE FROM LISTES WHERE id=?;";
    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES(nom, id_liste) VALUES(?,?);";
    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE id=?;";
    private static final String UPDATE_COCHE_ARTICLE = "UPDATE ARTICLES SET coche = 1 WHERE id=?;";
    private static final String UPDATE_DECOCHE_ARTICLE = "UPDATE ARTICLES SET coche = 0 WHERE id=?;";


    //    public void InsertArticle(int id_liste, String nom_article) throws BLLException {
//        try (Connection cnx = ConnectionProvider.getConnection();
//             PreparedStatement pstt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);) {
//            pstt.setString(1, nom_article);
//            pstt.setInt(2, id_liste);
//            pstt.executeUpdate();
//            ResultSet rs = pstt.getGeneratedKeys();
//            Article article = new Article(nom_article, id_liste);
//            if (rs.next()) {
//                //On affecte la clé récupérée à l'id
//                listeCourse.setId(rs.getInt(1));
//            }
//            }catch(SQLException sqlException){
//                throw new BLLException("Erreur lors de l'ajout de l'article");
//            }
//        }
    @Override
    public void insert(ListeCourse listeCourse) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection(); // Ouverture de la connexion
             //Préparation de la requête SQL
             PreparedStatement pstt = cnx.prepareStatement(INSERT_LISTE, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement pstt2 = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);) {
            if (listeCourse.getId() == 0) { // Permet de gérer l'insertion d'un article seul
                // Définition de la requête SQL
                pstt.setString(1, listeCourse.getNom());
                //Execution de la requête
                pstt.executeUpdate();

                //Récupération de la clé générée dans le ResulSet
                ResultSet rs = pstt.getGeneratedKeys();
                //Si on récupère des lignes on se positionne sur la première
                if (rs.next()) {
                    //On affecte la clé récupérée à l'id
                    listeCourse.setId(rs.getInt(1));
                }
                rs.close();
            }
            ResultSet rs2;
            for (Article article : listeCourse.getListeArticles()) {
                pstt2.setString(1, article.getNom());
                pstt2.setInt(2, listeCourse.getId());
                pstt2.executeUpdate();
                rs2 = pstt2.getGeneratedKeys();
                if (rs2.next()) {
                    article.setId(rs2.getInt(1));
                }
                rs2.close();
            }
            cnx.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DALException(("une erreur est survenue lors de l'insertion de la liste"));
        }
    }

    @Override
    public void delete(int idListe) throws DALException {

        try (Connection cnx = ConnectionProvider.getConnection(); //Ouverture de la connexion
             PreparedStatement pstt = cnx.prepareStatement(DELETE_LISTE);) {

            pstt.setInt(1, idListe);
            pstt.executeUpdate();

        } catch (SQLException throwables) {
            throw new DALException("Une erreur est survenue lors de la suppression de la liste");
        }
    }

    @Override
    public List<ListeCourse> selectAll() throws DALException {
        List<ListeCourse> listeCourses = new ArrayList<ListeCourse>();
        try (Connection cnx = ConnectionProvider.getConnection();
             Statement stt = cnx.createStatement()) {
            //J'execute la requete
            ResultSet rs = stt.executeQuery(SELECT_ALL);
            ListeCourse Courses;

            // Je parcours une à une toutes les listedeCourse
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                //Je stocke leur caractéristique dans une instance de l'objet listeCourse
                Courses = new ListeCourse(id, nom);
                //j'ajoute chaque listeCourse à ma Liste de listeCourse
                listeCourses.add(Courses);
            }

        } catch (SQLException sqlException) {
            throw new DALException("Erreur lors de l'affiche des listes");

        }
        return listeCourses;
    }

    @Override
    public ListeCourse SelectById(int id) throws DALException {
        ListeCourse listeCourse = new ListeCourse();
        //Ouverture d'une connection et préparation de la requête SQL
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(SELECT_BY_ID);
        ) {

            pstt.setInt(1, id);
            //Execution de la requête
            ResultSet rs = pstt.executeQuery();
            //On se place sur la première ligne
            while (rs.next()) {
                // on récupère les éléments de chaque colonne dans des variables
                int id_Liste = rs.getInt("id_liste");
                String nom_liste = rs.getString("nom_liste");
                //J'affecte la valeur de ces variables à listeCourse
                listeCourse.setId(id_Liste);
                listeCourse.setNom(nom_liste);
                //Je check la présence d'article dans cette liste
                if (rs.getString("nom_article") != null) {
                    //s'il y a des articles, je stocke ses attributs dans des variables
                    int id_article = rs.getInt("id_article");
                    String nom_article = rs.getString("nom_article");
                    Boolean coche = rs.getBoolean("coche");
                    //J'utilise les valeurs récupérées pour créer un nouvelle article...
                    Article article = new Article(id_article, nom_article, coche);
                    //...que j'ajoute à ma liste à afficher
                    listeCourse.getListeArticles().add(article);

                } //S'il n'y a pas d'articles, on ne fait rien
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new DALException("Erreur lors de l'affichage de la liste");
        }
        return listeCourse;
    }

    @Override
    public void deleteArticle(int IdArticle) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection(); //Ouverture de la connexion
             PreparedStatement pstt = cnx.prepareStatement(DELETE_ARTICLE);) {

            pstt.setInt(1, IdArticle);
            pstt.executeUpdate();

        } catch (SQLException throwables) {
            throw new DALException("Une erreur est survenue lors de la suppression de l'article'");
        }

    }

    @Override
    public void cocherArticle(int IdArticle) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(UPDATE_COCHE_ARTICLE);
        ) {
            pstt.setInt(1, IdArticle);
            pstt.executeUpdate();

        } catch (SQLException sqlException) {
            throw new DALException("Une erreur est survenue lors du cochage de l'article");
        }


    }

    @Override
    public void decocherArticle(int IdArticle) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection();
             PreparedStatement pstt = cnx.prepareStatement(UPDATE_DECOCHE_ARTICLE);
        ) {
            pstt.setInt(1, IdArticle);
            pstt.executeUpdate();

        } catch (SQLException sqlException) {
            throw new DALException("Une erreur est survenue lors du cochage de l'article");
        }

    }

}
