<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value='/res/css/signin.css' />" rel="stylesheet" type="text/css" />
<div class="container-fluid">
    <c:if test="${param.error != null}">
        <p style="color: red">Datos de login incorrectos!</p>
    </c:if>
    <form class="form-signin" name="f" action="<c:url value='/login'/>" method="POST">
        <h2 class="form-signin-heading">Acceso de usuarios</h2>
        <label for="inputEmail" class="sr-only">Dirección de email</label>
        <input id="inputEmail" class="form-control" placeholder="Dirección de email" name="usuario" required="" autofocus="" type="email">
        <label for="inputPassword" class="sr-only">Contraseña</label>
        <input id="inputPassword" class="form-control" placeholder="Contraseña" name="clave" required="" type="password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="checkbox">
            <label>
                <input name="recuerdame" value="remember-me" type="checkbox"> Recuerdame
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Acceder</button>
    </form>

</div>