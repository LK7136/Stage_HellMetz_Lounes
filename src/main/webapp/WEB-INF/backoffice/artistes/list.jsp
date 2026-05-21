<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Programmation</div>
            <div class="hm-card-title">Artistes du HellMetz Festival</div>
        </div>
        <%-- Lien vers le formulaire de création --%>
        <a href="${pageContext.request.contextPath}/backoffice/artistes/edit" class="hm-btn-primary">
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
                        <th>Photo</th>
                        <th>Artiste / Groupe</th>
                        <th>Style</th>
                        <th>Nationalité</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%-- On boucle sur la liste "artistes" --%>
                    <c:forEach var="a" items="${artistes}">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty a.url_photo}">
                                        <img src="${pageContext.request.contextPath}/${a.url_photo}"
                                             alt="Photo de ${a.nom}"
                                             style="width: 50px; height: 50px; border-radius: 4px;">
                                    </c:when>

                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/backoffice/images/artistes/defaultAvatar.png"
                                             alt="Photo par défaut"
                                             style="width: 50px; height: 50px; border-radius: 4px;">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <strong>${a.nom_scene}</strong><br>
                                <small style="color: #666;">${a.prenom} ${a.nom}</small>
                            </td>
                            <td>
                                <span class="hm-pill">${a.styleLibelle}</span>
                            </td>
                            <td>${a.nationalite}</td>
                            <td>
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/artistes/edit?id=${a.id}">
                                    ✏️ éditer
                                </a>
                                &nbsp;|&nbsp;
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/artistes/delete?id=${a.id}"
                                   onclick="return confirm('Supprimer l\'artiste ${a.id} ?');"
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