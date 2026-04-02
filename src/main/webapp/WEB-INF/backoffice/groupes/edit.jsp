<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Édition</div>
            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${not empty groupe.id}">Modifier le groupe : ${groupe.nom}</c:when>
                    <c:otherwise>Nouveau groupe</c:otherwise>
                </c:choose>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/groupes" class="hm-btn-primary" style="background-color: #6c757d; color: white;">
            <span>←</span>
            <span>Retour à la liste</span>
        </a>
    </div>

    <div class="p-4">
        <form action="${pageContext.request.contextPath}/backoffice/groupes/edit" method="post">

            <input type="hidden" name="id" value="${groupe.id}">
            <input type="hidden" name="id_concert" value="${groupe.id_concert}">

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="nom" class="form-label fw-bold">Nom du groupe <span class="text-danger">*</span></label>
                    <input type="text" id="nom" name="nom" class="form-control" value="${groupe.nom}" required>
                </div>
                <div class="col-md-6">
                    <label for="annee_creation" class="form-label fw-bold">Année de création</label>
                    <input type="number" id="annee_creation" name="annee_creation" class="form-control" value="${groupe.annee_creation}">
                </div>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label fw-bold">Description</label>
                <textarea id="description" name="description" class="form-control" rows="4">${groupe.description}</textarea>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="pays_origine" class="form-label fw-bold">Pays d'origine</label>
                    <input type="text" id="pays_origine" name="pays_origine" class="form-control" value="${groupe.pays_origine}">
                </div>
                <div class="col-md-6">
                    <label for="ville_origine" class="form-label fw-bold">Ville d'origine</label>
                    <input type="text" id="ville_origine" name="ville_origine" class="form-control" value="${groupe.ville_origine}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-4">
                    <label for="site_web" class="form-label fw-bold">Site Web</label>
                    <input type="url" id="site_web" name="site_web" class="form-control" value="${groupe.site_web}">
                </div>
                <div class="col-md-4">
                    <label for="url_youtube" class="form-label fw-bold">Chaîne YouTube</label>
                    <input type="url" id="url_youtube" name="url_youtube" class="form-control" value="${groupe.url_youtube}">
                </div>
                <div class="col-md-4">
                    <label for="url_logo" class="form-label fw-bold">URL Logo</label>
                    <input type="url" id="url_logo" name="url_logo" class="form-control" value="${groupe.url_logo}">
                </div>
            </div>

            <div class="form-check form-switch mb-4 mt-3">
                <input class="form-check-input" type="checkbox" id="actif" name="actif" value="true" ${groupe.actif ? 'checked' : ''}>
                <label class="form-check-label fw-bold text-success" for="actif">Groupe actif (programmé au festival)</label>
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