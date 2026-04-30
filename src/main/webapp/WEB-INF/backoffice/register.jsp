<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Création d'un compte - HellMetz</title>
    <link href="${pageContext.request.contextPath}/backoffice/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/backoffice/css/backoffice.css" rel="stylesheet">
</head>
<body>
    <div class="container connexion mt-5">
        <form class="form-login" method="post"
              action="${pageContext.request.contextPath}/register">
            <h2 class="form-login-heading">Création d'un compte utilisateur</h2>

        <%-- Affichage d'un éventuel message d'erreur --%>
            <c:if test="${not empty erreur}">
                <div class="erreurCnx"><p>${erreur}</p></div>
            </c:if>
        <%-- ====== INFORMATIONS DE L'UTILISATEUR ====== --%>
            <div class="mb-3">
                <label class="form-label">Nom</label>
                    <input type="text" class="form-control" name="nom" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Prénom</label>
                <input type="text" class="form-control" name="prenom" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" class="form-control" name="email" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Identifiant</label>
                <input type="text" class="form-control" name="identifiant" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mot de passe</label>
                <input type="password" class="form-control" name="motDePasse" required>
            </div>
            <%-- ====== CHOIX DU RÔLE ====== --%>
            <div class="mb-3">
                <label class="form-label">Rôle de l'utilisateur</label>
                <select class="form-select" name="idRole" id="selectRole" required>
                    <option value="">-- Choisir un rôle --</option>
                    <c:forEach var="r" items="${roles}">
                        <option value="${r.idRole}" data-code="${r.codeRole}">
                                ${r.libelle}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <%-- ====== CHOIX DES PERMISSIONS (cases à cocher) ====== --%>
            <%-- Si rôle ADMIN : toutes les cases sont cochées et désactivées --%>
            <div class="mb-3">
                <label class="form-label">Permissions accordées</label>
                <div id="zonePermissions" class="border rounded p-2">
                    <c:forEach var="p" items="${permissions}">
                        <div class="form-check">
                            <input class="form-check-input chkPermission"
                                   type="checkbox"
                                   name="idsPermissions"
                                   value="${p.idPermission}"
                                   id="perm_${p.idPermission}">
                            <label class="form-check-label" for="perm_${p.idPermission}">
                                <strong>${p.codePermission}</strong> - ${p.libelle}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Créer le compte</button>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary">
                Annuler
            </a>
        </form>
    </div>

    <%-- ====== Script : si rôle = ADMIN, on coche toutes les permissions ====== --%>
    <script>
        document.getElementById("selectRole").addEventListener("change", function () {
            var code = this.options[this.selectedIndex].getAttribute("data-code");
            var cases = document.querySelectorAll(".chkPermission");
            if (code === "ADMIN") {
                cases.forEach(function (c) { c.checked = true; c.disabled = true; });
            } else {
                cases.forEach(function (c) { c.disabled = false; c.checked = false; });
            }
        });
    </script>
</body>
</html>
