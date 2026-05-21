<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Identification - HellMetz Backoffice</title>
  <link href="${pageContext.request.contextPath}/backoffice/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/backoffice/css/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/backoffice/css/templatemo-festava-live.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/backoffice/css/backoffice.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5" id="login-page">
  <div class="row justify-content-center">
    <div class="col-md-6 col-lg-4">


      <form class="form-login p-4 border rounded shadow bg-white" method="post" action="${pageContext.request.contextPath}/login">

        <h2 class="form-login-heading text-center mb-4">Identification</h2>

        <c:if test="${not empty erreur}">
          <div class="alert alert-danger" role="alert">
              ${erreur}
          </div>
        </c:if>

        <div class="mb-3">
          <input type="text" class="form-control" name="identifiant" placeholder="Identifiant" required autofocus />
        </div>

        <div class="mb-3">
          <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe" required />
        </div>

        <div class="mb-3 text-end login-social-link">
          <a data-bs-toggle="modal" href="#modalMdpOublie" class="text-decoration-none">
            Mot de passe oublié ?
          </a>
        </div>

        <button class="btn custom-btn w-100" type="submit">
          <i class="bi bi-box-arrow-in-right"></i> Se connecter
        </button>

      </form>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/backoffice/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/backoffice/js/bootstrap.min.js"></script>
</body>
</html>