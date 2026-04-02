<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Programmation</div>
            <div class="hm-card-title">Artistes du HellMetz Festival</div>
        </div>
        <%-- Lien vers le formulaire de création --%>
        <a href="${pageContext.request.contextPath}/backoffice/artiste/edit.jsp" class="hm-btn-primary">
            <span>＋</span>
            <span>Nouvel artiste</span>
        </a>
    </div>

    <c:choose>
        <%-- On vérifie si la liste "artistes" est vide --%>
        <c:when test="${empty artistes}">
            <div style="padding: 40px; text-align: center;">
                <p>Aucun artiste enregistré pour le moment.</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="hm-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Artiste / Groupe</th>
                        <th>Style</th>
                        <th>Nationalité</th>
                        <th>Cachet</th>
                        <th>Liens</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%-- On boucle sur la liste "artistes" --%>
                    <c:forEach var="a" items="${artistes}">
                        <tr>
                            <td>${a.id}</td>
                            <td>
                                <strong>${a.nom_scene}</strong><br>
                                <small style="color: #666;">${a.prenom} ${a.nom}</small>
                            </td>
                            <td>
                                    <%-- id_style correspond à ta FK dans la base --%>
                                <span class="hm-pill">Style #${a.id_style}</span>
                            </td>
                            <td>${a.nationalite}</td>
                            <td>
                                <strong>${a.cachet} €</strong>
                            </td>
                            <td>
                                <div class="social-links">
                                    <c:if test="${not empty a.url_spotify}">
                                        <a href="${a.url_spotify}" target="_blank" title="Spotify">🎧</a>
                                    </c:if>
                                    <c:if test="${not empty a.url_instagram}">
                                        <a href="${a.url_instagram}" target="_blank" title="Instagram">📸</a>
                                    </c:if>
                                </div>
                            </td>
                            <td>
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/artiste/edit.jsp?id=${a.id}">
                                    ✏️ éditer
                                </a>
                                &nbsp;|&nbsp;
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/artiste/delete?id=${a.id}"
                                   onclick="return confirm('Supprimer définitivement l\'artiste ${a.nom_scene} ?');"
                                   style="color: #e74c3c;">
                                    🗑️ supprimer
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>