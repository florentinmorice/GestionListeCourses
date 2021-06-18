<%--
  Created by IntelliJ IDEA.
  User: fmorice2021
  Date: 16/06/2021
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Panier</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.2/css/bulma.min.css">
</head>
<body>
<section class="hero is-primary">
    <div class="hero-body has-text-centered">
        <p class="title">
            COURSES
        </p>
        <p class="subtitle">
            Votre panier
        </p>
    </div>
</section>
<div class="container">
    <div class="columns is-mobile is-centered">
        <div class="column is-half">
            <form action="${pageContext.request.contextPath}/panier" method="post"></form>
            <article class="panel">
                <p class="panel-heading">
                    Votre liste
                </p>
                <c:forEach items="${listeCourse.listeArticles}" var="article">
                    <a class="panel-block">
    <span class="panel-icon">
      <i class="fas fa-book" aria-hidden="true"></i>
    </span>
                        <input type="hidden" name="id_liste" value="${listeCourse.id}"/>
                        <input type="hidden" name="id_article" value="${article.id}"/>
                        <label class="checkbox">
                            <input type="checkbox">
                                ${article.nom}
                        </label>
                    </a>
                </c:forEach>
            </article>
            </form>
        </div>
    </div>
</div>
<div class="has-text-centered" style="margin-top: 10px">
    <a href="${pageContext.request.contextPath}/listes" title="Retour accueil">
        <button class="button is-primary is-hovered">Retour à la page d'accueil</button>
    </a>
</div>
</body>
</html>
