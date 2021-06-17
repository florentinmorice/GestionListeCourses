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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/fragments/header.jsp">
    <jsp:param name="titre" value="PANIER"/>
</jsp:include>

<h1> Votre Liste </h1>
<form action="${pageContext.request.contextPath}/panier" method="post"></form>
<c:forEach items="${listeCourse.listeArticles}" var="article">
    <li>
            <input type="hidden" name="id_liste" value="${listeCourse.id}"/>
            <input type="hidden" name="id_article" value="${article.id}"/>
            ${article.nom}
        <input type="checkbox" name="coche" onclick="this.form.submit()" ${article.coche?"checked":""}></input>
    </li>
</c:forEach>
</form>

<a href="${pageContext.request.contextPath}/listes" title="Retour accueil">Retourner à l'accueil</a>

<jsp:include page="/WEB-INF/fragments/footer.jsp">
    <jsp:param name="titre" value="panier"/>
</jsp:include>
</body>
</html>
