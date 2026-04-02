<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Gestion</div>
            <div class="hm-card-title">Concerts du HellMetz Festival</div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/concerts/edit" class="hm-btn-primary">
            <span>＋</span>
            <span>Nouveau concert</span>
        </a>
    </div>

    <c:choose>
        <c:when test="${empty concerts}">
            <p>Aucun concert enregistré pour le moment.</p>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="hm-table">
                    <thead>
                    <tr>
                        <th>Scène</th>
                        <th>Groupe</th>
                        <th>Statut</th>
                        <th>Début</th>
                        <th>Fin</th>
                        <th>Balance début</th>
                        <th>Balance fin</th>
                        <th>Décibels max</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${concerts}">
                        <tr>
                            <td>${c.nom_scene}</td>   <%-- ✅ au lieu de c.id_scene --%>
                            <td>${c.nom_groupe}</td>  <%-- ✅ nouveau --%>
                            <td><span class="hm-pill">${c.statut}</span></td>
                            <td>${c.date_heure_debut}</td>
                            <td>${c.date_heure_fin}</td>
                            <td>${c.date_balance_debut}</td>
                            <td>${c.date_balance_fin}</td>
                            <td><span class="hm-pill">${c.decibels_max} dB</span></td>
                            <td>
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/concerts/edit?id=${c.id_concert}">
                                    ✏️ éditer
                                </a>
                                &nbsp;|&nbsp;
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/concerts/delete?id=${c.id_concert}"
                                   onclick="return confirm('Supprimer ce concert ?');">
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