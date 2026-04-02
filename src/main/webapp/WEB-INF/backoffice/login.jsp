<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<div id="login-page">
  <div class="container connexion">
    <form class="form-login" method="post" action="${pageContext.request.contextPath}/login">
      <h2 class="form-login-heading">Identification utilisateur</h2>

      <div class="login-wrap">

        <c:if test="${not empty erreur}">
          <div class="erreurCnx">
            <p>${erreur}</p>
          </div>
        </c:if>

        <input type="text" class="form-control" name="identifiant" placeholder="Login" required autofocus />
        <br>
        <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe" required />

