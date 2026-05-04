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

        <%-- INFORMATIONS DE L'UTILISATEUR --%>
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

        <%-- CHOIX DU RÔLE --%>
        <div class="mb-3">
            <label class="form-label">Rôle de l'utilisateur</label>
            <select class="form-select" name="idRole" id="selectRole" required>
                <option value="">-- Choisir un rôle --</option>
                <c:forEach var="r" items="${roles}">
                    <option value="${r.idRole}">${r.libelle}</option>
                </c:forEach>
            </select>
        </div>

        <%-- PERMISSIONS --%>
        <div class="mb-3">
            <label class="form-label">Permissions accordées</label>
            <div id="zonePermissions" class="border rounded p-2">
                <c:forEach var="p" items="${permissions}">
                    <div class="form-check">
                        <input class="form-check-input chkPermission"
                               type="checkbox"
                               name="idsPermissions"
                               value="${p.idPermission}"
                               id="perm_${p.idPermission}"
                               disabled>
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

<script>
    var permissionsParRole = ${permissionsParRole};

    document.getElementById("selectRole").addEventListener("change", function () {
        var idRole = this.value;
        var cases = document.querySelectorAll(".chkPermission");

        // Réinitialiser toutes les cases
        cases.forEach(function (c) {
            c.checked = false;
            c.disabled = true;
        });

        if (idRole && permissionsParRole[idRole]) {
            var permsAutorisees = permissionsParRole[idRole];
            cases.forEach(function (c) {
                if (permsAutorisees.includes(parseInt(c.value))) {
                    c.checked = true;
                }
            });
        }
    });
</script>
</body>
</html>