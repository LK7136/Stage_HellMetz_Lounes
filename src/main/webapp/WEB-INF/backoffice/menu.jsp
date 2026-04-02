<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
    <div class="backoffice-logo">HellMetz</div>
    <div class="backoffice-subtitle">Backoffice organisateur</div>

    <nav class="backoffice-nav">
        <a href="${pageContext.request.contextPath}/backoffice/dashboard"
           class="<c:if test='${activeMenu eq "dashboard"}'>active</c:if>">
            <span>🏠</span>
            <span>Tableau de bord</span>
        </a>

        <a href="${pageContext.request.contextPath}/backoffice/scenes"
           class="${activeMenu eq 'scenes' ? 'active' : ''}">
            <span>🪩</span>
            <span>Scenes</span>
        </a>

        <a href="${pageContext.request.contextPath}/backoffice/artiste"
           class="${activeMenu eq 'artiste' ? 'active' : ''}">
            <span>🧑‍🎤</span>
            <span>Artistes</span>
        </a>

        <a href="${pageContext.request.contextPath}/backoffice/groupes"
           class="<c:if test='${activeMenu eq "groupes"}'>active</c:if>">
            <span>🎸</span>
            <span>Groupes</span>
        </a>

        <a href="${pageContext.request.contextPath}/backoffice/concerts"
           class="<c:if test='${activeMenu eq "concerts"}'>active</c:if>">
            <span>🎵</span>
            <span>Concerts</span>
        </a>

        <a class="disabled">
            <span>📦</span>
            <span>Logistique & Matériel (à venir)</span>
        </a>

        <a class="disabled">
            <span>🧑‍🤝‍🧑</span>
            <span>RH & Bénévoles (à venir)</span>
        </a>

        <a class="disabled">
            <span>📍</span>
            <span>Points d’intérêt (à venir)</span>
        </a>
    </nav>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/backoffice/dashboard">HellMetz - Backoffice</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/backoffice/groupes">Gestion des Groupes</a>
                </li>
                <c:if test="${utilisateurConnecte.hasPermission('ADMIN')}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/backoffice/utilisateurs">Utilisateurs</a>
                    </li>
                </c:if>
            </ul>

            <ul class="navbar-nav ms-auto">
                <c:if test="${not empty utilisateurConnecte}">
                    <li class="nav-item d-flex align-items-center">
                        <span class="nav-link text-light me-3">
                            <i class="bi bi-person-circle"></i> Bonjour, <strong>${utilisateurConnecte.prenom} ${utilisateurConnecte.nom}</strong>
                         </span>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-danger btn-sm mt-1" href="${pageContext.request.contextPath}/logout">
                            <i class="bi bi-box-arrow-right"></i> Se déconnecter
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

