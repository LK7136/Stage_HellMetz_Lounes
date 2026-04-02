<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Édition</div>
            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${not empty concert.id_concert}">Modifier le concert #${concert.id_concert}</c:when>
                    <c:otherwise>Nouveau concert</c:otherwise>
                </c:choose>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/concerts" class="hm-btn-primary" style="background-color: #6c757d; color: white;">
            <span>←</span>
            <span>Retour à la liste</span>
        </a>
    </div>

    <div class="p-4">
        <form action="${pageContext.request.contextPath}/backoffice/concerts/edit" method="post">

            <input type="hidden" name="id" value="${concert.id_concert}">

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="id_scene" class="form-label fw-bold">ID Scène <span class="text-danger">*</span></label>
                    <input type="number" id="id_scene" name="id_scene" class="form-control" value="${concert.id_scene}" required>
                </div>
                <div class="col-md-6">
                    <label for="id_edition" class="form-label fw-bold">ID Édition <span class="text-danger">*</span></label>
                    <input type="number" id="id_edition" name="id_edition" class="form-control" value="${concert.id_edition}" required>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="date_heure_debut" class="form-label fw-bold">Date et heure de début</label>
                    <input type="datetime-local" id="date_heure_debut" name="date_heure_debut" class="form-control" value="${concert.date_heure_debut}">
                </div>
                <div class="col-md-6">
                    <label for="date_heure_fin" class="form-label fw-bold">Date et heure de fin</label>
                    <input type="datetime-local" id="date_heure_fin" name="date_heure_fin" class="form-control" value="${concert.date_heure_fin}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="date_balance_debut" class="form-label fw-bold">Balance début</label>
                    <input type="datetime-local" id="date_balance_debut" name="date_balance_debut" class="form-control" value="${concert.date_balance_debut}">
                </div>
                <div class="col-md-6">
                    <label for="date_balance_fin" class="form-label fw-bold">Balance fin</label>
                    <input type="datetime-local" id="date_balance_fin" name="date_balance_fin" class="form-control" value="${concert.date_balance_fin}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="decibels_max" class="form-label fw-bold">Décibels max</label>
                    <input type="number" id="decibels_max" name="decibels_max" class="form-control"
                           value="${not empty concert.id_concert ? concert.decibels_max : 95}" required>
                </div>
                <div class="col-md-6">
                    <%-- ✅ Select au lieu de checkbox --%>
                    <label for="statut" class="form-label fw-bold">Statut</label>
                    <select id="statut" name="statut" class="form-control">
                        <option value="Programmé"     ${concert.statut == 'Programmé'     ? 'selected' : ''}>Programmé</option>
                        <option value="Annulé"        ${concert.statut == 'Annulé'        ? 'selected' : ''}>Annulé</option>
                        <option value="Non programmé" ${concert.statut == 'Non programmé' ? 'selected' : ''}>Non programmé</option>
                    </select>
                </div>
            </div>

            <div class="text-end mt-4 pt-3 border-top">
                <button type="submit" class="hm-btn-primary" style="border: none;">
                    <span>💾</span>
                    <span>Enregistrer les modifications</span>
                </button>
            </div>

        </form>
    </div>
</div>