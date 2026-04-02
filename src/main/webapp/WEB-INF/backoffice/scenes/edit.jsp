<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Édition</div>
            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${not empty scene.id}">Modifier la scène : ${scene.nom}</c:when>
                    <c:otherwise>Nouvelle scène</c:otherwise>
                </c:choose>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/scenes" class="hm-btn-primary" style="background-color: #6c757d; color: white; text-decoration: none;">
            <span>←</span>
            <span>Retour à la liste</span>
        </a>
    </div>

    <div class="p-4">
        <form action="${pageContext.request.contextPath}/backoffice/scenes/edit" method="post" enctype="multipart/form-data">

            <input type="hidden" name="id" value="${scene.id}">

            <%-- Conserve l'URL du plan existant si aucun nouveau fichier n'est uploadé --%>
            <input type="hidden" name="urlPlanTechnique" value="${scene.urlPlanTechnique}">

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="nom" class="form-label fw-bold">Nom de la scène <span class="text-danger">*</span></label>
                    <input type="text" id="nom" name="nom" class="form-control" value="${scene.nom}" required>
                </div>
                <div class="col-md-6">
                    <label for="type" class="form-label fw-bold">Type</label>
                    <input type="text" id="type" name="type" class="form-control" value="${scene.type}">
                </div>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label fw-bold">Description</label>
                <textarea id="description" name="description" class="form-control" rows="3">${scene.description}</textarea>
            </div>

            <div class="row mb-3">
                <div class="col-md-3">
                    <label for="capacite" class="form-label fw-bold">Capacité</label>
                    <input type="number" id="capacite" name="capacite" class="form-control" value="${scene.capacite}">
                </div>
                <div class="col-md-3">
                    <label for="superficie" class="form-label fw-bold">Superficie (m²)</label>
                    <input type="number" id="superficie" name="superficie" class="form-control" value="${scene.superficie}">
                </div>
                <div class="col-md-6">
                    <label for="file_plan" class="form-label fw-bold">Changer le Plan Technique (PDF)</label>
                    <input type="file" id="file_plan" name="file_plan" class="form-control" accept="application/pdf">
                    <c:if test="${not empty scene.urlPlanTechnique}">
                        <small class="text-muted">Fichier actuel : ${scene.urlPlanTechnique}</small>
                    </c:if>
                </div>
            </div>

            <div class="form-check form-switch mb-4 mt-3">
                <input class="form-check-input" type="checkbox" id="actif" name="actif" value="true" ${scene.actif ? 'checked' : ''}>
                <label class="form-check-label fw-bold text-success" for="actif">Scène active</label>
            </div>

            <c:if test="${not empty scene.urlPlanTechnique}">
                <div class="mt-4 border-top pt-3">
                    <label class="fw-bold mb-2">Aperçu du Plan Technique actuel</label>
                    <div style="background: #f8f9fa; border-radius: 8px; padding: 10px; border: 1px solid #dee2e6;">
                        <iframe src="${pageContext.request.contextPath}${scene.urlPlanTechnique}"
                                width="100%" height="500px"
                                style="border:none; border-radius:4px;">
                        </iframe>
                    </div>
                </div>
            </c:if>

            <div class="text-end mt-4 pt-3 border-top">
                <button type="submit" class="hm-btn-primary" style="border: none;">
                    <span>💾</span>
                    <span>Enregistrer les modifications</span>
                </button>
            </div>
        </form>
    </div>
</div>