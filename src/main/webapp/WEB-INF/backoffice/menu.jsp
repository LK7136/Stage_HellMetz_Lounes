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
            <span>🪩</span>
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
