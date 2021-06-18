<%--
  Created by IntelliJ IDEA.
  User: fmorice2021
  Date: 15/06/2021
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Listes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>
<body class="container">

<div>
    <jsp:include page="/WEB-INF/fragments/header.jsp">
        <jsp:param name="titre" value="LISTES PREDEFINIES"/>
    </jsp:include>
</div>
<div class="col-12">
    <div class="row">
        <ul class="list-group col-12">
            <c:forEach items="${listeListeCourse}" var="listeCourse">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <a href="${pageContext.request.contextPath}/nouvelle?id=${listeCourse.id}"
                       title="Modifier la liste">
                        <button type="button" class="btn btn-outline-primary">${listeCourse.nom}</button>
                    </a>
                    <div>
                        <a href="${pageContext.request.contextPath}/panier?id=${listeCourse.id}"
                           title="Commencer ses courses"><i class="bi bi-basket fs-2 m-2"></i></a>
                        <a href="${pageContext.request.contextPath}/listes?supprimer=${listeCourse.id}"
                           title="Supprimer"><i class="bi bi-trash-fill fs-2 m-2"></i></a>
                    </div>
                </li>

            </c:forEach>

        </ul>
    </div>
</div>
<div>

</div>
<footer class="container">
    <div class="d-flex justify-content-center mx-auto m-5 fs-1">

        <a href="${pageContext.request.contextPath}/nouvelle" title="Créer une nouvelle liste"><i
                class="bi bi-file-plus-fill"></i></a>

    </div>
</footer>

</body>
</html>
