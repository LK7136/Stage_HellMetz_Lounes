<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Édition</div>
            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${not empty artiste.id}">Modifier l'artiste #${artiste.nom}</c:when>
                    <c:otherwise>Nouvel artiste</c:otherwise>
                </c:choose>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/artistes" class="hm-btn-primary" style="background-color: #6c757d; color: white;">
            <span>←</span>
            <span>Retour à la liste</span>
        </a>
    </div>

    <div class="p-4">
        <form action="${pageContext.request.contextPath}/backoffice/artistes/edit" method="post" enctype="multipart/form-data">

            <input type="hidden" name="id" value="${artiste.id}">
            <input type="hidden" name="id_groupe" value="${artiste.id_groupe}">

            <input type="hidden" name="url_photo" value="${artiste.url_photo}">


            <%--Nouvelle partie photo--%>
            <div class="row mb-3">
                <div class="col-auto" style="text-align: center">
                    <label for="urlPhotoArtiste" style="cursor: pointer; display: inline-block;">
                        <c:choose>
                            <c:when test="${not empty artiste.url_photo}">
                                <img id="preview" src="${pageContext.request.contextPath}/${artiste.url_photo}"
                                     alt="Photo de ${artiste.nom}"
                                     style="width: 180px; height: 180px; border-radius: 5%; object-fit: cover; border: 2px;">
                            </c:when>
                            <c:otherwise>
                                <img id="preview" src="${pageContext.request.contextPath}/backoffice/images/artistes/defaultAvatar.png"
                                     alt="Photo par défaut"
                                     style="width: 180px; height: 180px; border-radius: 5%; object-fit: cover; border: 2px;">
                            </c:otherwise>
                        </c:choose>
                    </label>

                    <input width="100px" height="100px" type="file" id="urlPhotoArtiste" name="urlPhotoArtiste" accept="image/*" style="display: none;">

                    <c:if test="${not empty artiste.url_photo}">
                        <div style="margin-top: 6px;">
                                <button type="button" id="btnSupprimerPhoto" name="supprimer_photo" value="true" class="hm-btn-primary" style="background-color: #6c757d; color: white;">
                                    🗑️ Supprimer la photo
                                </button>
                            </label>
                        </div>
                    </c:if>
                </div>

                <div class="col mb-3" style="display: flex; flex-direction: column; justify-content: space-between; height: 180px;">
                    <div class="row mb-3">
                        <div class="col-md-6 ">
                            <label for="nom" class="form-label fw-bold">Nom<span class="text-danger">*</span></label>
                            <input type="text" id="nom" name="nom" class="form-control " value="${artiste.nom}" required>
                        </div>
                        <div class="col-md-6 ">
                            <label for="prenom" class="form-label fw-bold">Prénom<span class="text-danger">*</span></label>
                            <input type="text" id="prenom" name="prenom" class="form-control" value="${artiste.prenom}" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="nom_scene" class="form-label fw-bold">Nom de scène</label>
                            <input type="text" id="nom_scene" name="nom_scene" class="form-control" value="${artiste.nom_scene}">
                        </div>
                        <div class="col-md-6">
                            <label for="nationalite" class="form-label fw-bold">Nationalité</label>
                            <input type="text" id="nationalite" name="nationalite" class="form-control" value="${artiste.nationalite}">
                        </div>
                    </div>
                </div>
            </div>


            <div class="row mb-3">
                <div class="col-12">
                    <label for="biographie" class="form-label fw-bold">Biographie</label>
                    <textarea id="biographie" name="biographie" class="form-control" style="height: 200px">${artiste.biographie}</textarea>
                </div>
            </div>

            <div class="row mb-1">
                <div class="col-md-6">
                    <label for="id_style" class="form-label fw-bold">Style</label>
                    <select id="id_style" name="id_style" class="form-control">
                        <option value="">-- Sélectionner un style --</option>
                        <c:forEach var="style" items="${styles}">
                            <option value="${style.id}"
                                    <c:if test="${style.id == artiste.id_style}">selected</c:if>>
                                    ${style.libelle}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-6 pb-2">
                    <label for="exigences_catering" class="form-label fw-bold">Exigences</label>
                    <textarea id="exigences_catering" name="exigences_catering" class="form-control">${artiste.exigences_catering}</textarea>
                </div>
                <div>
                    <select id="nom_groupe" name="nom_groupe" class="form-control">
                        <option value="">-- Sélectionner un groupe --</option>
                        <c:forEach var="style" items="${styles}">
                            <option value="${style.id}"
                                    <c:if test="${style.id == artiste.id_style}">selected</c:if>>
                                    ${style.libelle}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>






<%--            Url des artiste--%>
<%--            <div class="row mb-3">--%>
<%--&lt;%&ndash;                <div class="row mb-3">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="col-md-6">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <label for="url_facebook" class="form-label fw-bold">Facebook</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="url" id="url_facebook" name="url_facebook" class="form-control" value="${artiste.url_facebook}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="col-md-6">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <label for="url_instagram" class="form-label fw-bold">Instagram</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="url" id="url_instagram" name="url_instagram" class="form-control" value="${artiste.url_instagram}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="col-md-6">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <label for="url_spotify" class="form-label fw-bold">Spotify</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="url" id="url_spotify" name="url_spotify" class="form-control" value="${artiste.url_spotify}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </div>&ndash;%&gt;--%>
<%--            </div>--%>

            <div class="text-end mt-4 pt-3 border-top">
                <button type="submit" class="hm-btn-primary" style="border: none;">
                    <span>💾</span>
                    <span>Enregistrer les modifications</span>
                </button>
            </div>

        </form>
    </div>
</div>

<%--Script pour prévisualiser l'image dans edit --%>
<script>
    // chemin de l'image par défaut pour les imaged des artistes
    const defaultAvatarUrl = "${pageContext.request.contextPath}/backoffice/images/artistes/defaultAvatar.png";

    // aperçu choix d'un nouveau fichier
    document.getElementById('urlPhotoArtiste').addEventListener('change', function(event) {
        const file = event.target.files[0];

        if (file) {
            const tempUrl = URL.createObjectURL(file);
            document.getElementById('preview').src = tempUrl;

            // Si on choisit finalement une nouvelle image, on annule la demande de suppression
            document.getElementById('supprimer_photo').value = "false";
            const btnSuppr = document.getElementById('btnSupprimerPhoto');
            if (btnSuppr) btnSuppr.style.display = "inline-block";
        }
    });

    // clic sur le bouton Supprimer Actualisation sans valider
    const btnSuppr = document.getElementById('btnSupprimerPhoto');
    if (btnSuppr) {
        btnSuppr.addEventListener('click', function() {
            // Change l'image immédiatement pour l'avatar par défaut
            document.getElementById('preview').src = defaultAvatarUrl;

            // Vide le fichier sélectionné
            document.getElementById('urlPhotoArtiste').value = "";

            // Met à jour la Servlet
            document.getElementById('supprimer_photo').value = "true";
            document.getElementById('url_photo').value = "";

            // Masque le bouton car plus rien à supprimer
            this.style.display = "none";
        });
    }
</script>