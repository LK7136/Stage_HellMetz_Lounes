<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>
        <c:out value="${pageTitle != null ? pageTitle : 'HellMetz - Backoffice'}"/>
    </title>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@200;400;600;700&display=swap" rel="stylesheet">

    <!-- CSS Bootstrap (si tu as bien copié le thème) -->
    <link href="${pageContext.request.contextPath}/backoffice/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/backoffice/css/bootstrap-icons.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/backoffice/css/templatemo-festava-live.css" rel="stylesheet">

    <!-- Styles custom backoffice -->
    <link href="${pageContext.request.contextPath}/backoffice/css/backoffice.css" rel="stylesheet">
</head>
<body>

<div class="backoffice-wrapper">

    <!-- SIDEBAR -->
    <aside class="backoffice-sidebar">
        <jsp:include page="menu.jsp"/>
    </aside>

    <!-- MAIN -->
    <main class="backoffice-main">
        <header class="backoffice-header">
            <h1>
                <c:out value="${pageTitle != null ? pageTitle : 'Backoffice HellMetz'}"/>
            </h1>
            <p class="subtitle">Interface d’administration du festival (groupes, concerts, logistique…)</p>
        </header>

        <section>
            <c:if test="${not empty contentPage}">
                <jsp:include page="${contentPage}"/>
            </c:if>
        </section>
    </main>

</div>

<!-- Scripts du thème (optionnels si déjà présents côté front) -->
<script src="${pageContext.request.contextPath}/backoffice/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/backoffice/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/backoffice/js/jquery.sticky.js"></script>
<script src="${pageContext.request.contextPath}/backoffice/js/click-scroll.js"></script>
<script src="${pageContext.request.contextPath}/backoffice/js/custom.js"></script>

</body>
</html>
