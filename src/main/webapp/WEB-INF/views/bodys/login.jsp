<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<link href="<c:url value='/res/css/signin.css' />" rel="stylesheet" type="text/css" />
<div class="container-fluid">
    <form class="form-signin" name="f" action="<c:url value='/login'/>" method="POST">
        <h2 class="form-signin-heading"><s:message code="form.login.title"/></h2>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <strong><s:message code="form.login.errorCAPS"/></strong> <s:message code="form.login.incorrectLogin"/>
            </div>
        </c:if>

        <label for="inputEmail" class="sr-only"><s:message code="form.login.emailOrUser"/></label>
        <input id="inputEmail" class="form-control" placeholder="<s:message code="form.login.emailOrUser"/>"
               name="usuario" required="" autofocus="" type="text">
        <label for="inputPassword" class="sr-only"><s:message code="form.login.pass"/></label>
        <input id="inputPassword" class="form-control" placeholder="<s:message code="form.login.pass"/>" name="clave"
               required="" type="password">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="checkbox">
            <label>
                <input name="recuerdame" value="remember-me" type="checkbox"> <s:message code="form.login.rememberme"/>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="form.login.submit"/></button>
    </form>

</div>