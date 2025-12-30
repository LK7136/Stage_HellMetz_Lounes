<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>HellMetz - Groupes</title>
</head>
<body>
<h1>Liste des groupes HellMetz</h1>

<p>
    <a href="${pageContext.request.contextPath}/">Retour au portail</a>
</p>

<table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nom</th>
        <th>Style</th>
        <th>Pays</th>
        <th>Description</th>
        <th>Site Web</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="g" items="${groupes}">
        <tr>
            <td>${g.id}</td>
            <td>${g.nom}</td>
            <td>${g.style}</td>
            <td>${g.pays}</td>
            <td>${g.description}</td>
            <td><a href="${g.siteWeb}" target="_blank">${g.siteWeb}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
