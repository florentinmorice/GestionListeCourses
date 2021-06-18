<%--
  Created by IntelliJ IDEA.
  User: fmorice2021
  Date: 15/06/2021
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Nouvelle Liste</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body class="container">
<jsp:include page="/WEB-INF/fragments/header.jsp">
    <jsp:param name="titre" value="NOUVELLE LISTE"/>
</jsp:include>

<div class="col-12">

    <form method="post" action="${pageContext.request.contextPath}/nouvelle">
        <div class="col-10">
            <c:if test="${ empty listeCourse}">
                <input class="form-control form-control-lg" type="text" placeholder="Saisir le nom de votre liste"
                       aria-label=".form-control-lg example" id="nom_liste" name="nom_liste">
            </c:if>
            <c:if test="${ !empty listeCourse}">
            <input type="hidden" value="${listeCourse.id}" name="id"/>
            <h1>${listeCourse.nom}</h1>
        </div>
        <div class="row justify-content-center">
            <ul class="list-group list-group-flush col-8 text-center">
                <c:forEach items="${listeCourse.listeArticles}" var="article">
                    <li class="list-group-item d-flex text-center justify-content-between align-items-center">${article.nom}
                        <a href="${pageContext.request.contextPath}/nouvelle?id_liste=${listeCourse.id}&id_article=${article.id}"
                           title="SupprimerArticle"><i class="bi bi-trash-fill fs-2 m-2"></i></a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        </c:if>
        <br>
        <div class="col-12">
            <div class="row g-3 justify-content-between">
                <div class="col-2 text-center">
                    <span class="material-icons">autorenew</span>
                </div>
                <div class=" row col-8 ">
                    <div class="col-8">
                        <label for="nom_article" class="visually-hidden">Nom de l'article</label>
                        <input type="text" class="form-control" id="nom_article" name="nom_article"
                               placeholder="Nom de l'article à ajouter">
                    </div>
                    <div class="col-4 text-center ml-2">
                        <button class="btn btn-primary align-middle" type="submit"><i class="bi bi-plus"></i></button>
                        <!--<input type="submit" class="btn btn-primary mb-3" value="Ajouter"> </input>-->
                    </div>
                </div>
                <div class="col-2 text-center"><span class="material-icons">bubble_chart</span></div>
            </div>
        </div>
        <!--
        <label for="nom_article">Nom de l'article</label>
        <input type="text" id="nom_article" name="nom_article">

        <input type="submit" value="Ajouter" class="btn btn-primary mb-3"></input>
        -->
    </form>

</div>

<jsp:include page="/WEB-INF/fragments/footer.jsp">
    <jsp:param name="titre" value="nouvelle liste"/>
</jsp:include>

</body>
</html>
