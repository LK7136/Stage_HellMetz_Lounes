<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Gestion</div>
            <div class="hm-card-title">Groupes du HellMetz Festival</div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/groupe/edit" class="hm-btn-primary">
            <span>＋</span>
            <span>Nouveau groupe</span>
        </a>
    </div>

    <c:choose>
        <c:when test="${empty groupes}">
            <p>Aucun groupe enregistré pour le moment.</p>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="hm-table">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Année Création</th>
                        <th>Pays</th>
                        <th>Site web</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="g" items="${groupes}">
                        <tr>
                            <td>
                                <strong>${g.nom}</strong>
                            </td>
                            <td>
                                <strong>${g.description}</strong>
                            </td>
                            <td>
                                <span class="hm-pill">${g.annee_creation}</span>
                            </td>
                            <td>${g.pays_origine}</td>
                            <td>
                                <c:if test="${not empty g.site_web}">
                                    <a class="hm-link-site"
                                       href="${g.site_web}"
                                       target="_blank" rel="noopener noreferrer">
                                        ouvrir
                                    </a>
                                </c:if>
                            </td>
                            <td>
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/groupe/edit?id=${g.id}">
                                    ✏️ éditer
                                </a>
                                &nbsp;|&nbsp;
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/groupe/delete?id=${g.id}"
                                   onclick="return confirm('Supprimer ce groupe ?');">
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
