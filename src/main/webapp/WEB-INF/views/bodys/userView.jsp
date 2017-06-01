<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <c:choose>
        <c:when test="${user != null}">
            <div class="jumbotron">
                <div class="jumbotron">
                    <span class="h1"><spring:message code="user.profile"/></span>
                </div>
                <br>
                <div class="well">
                    <i class="fa fa-user fa-3x"></i>
                    <strong class="h2"><spring:message code="form.user.username"/>: </strong>
                    <span class="h3"><c:out value="${user.username}"/></span>
                </div>
                <br>
                <div class="well">
                    <i class="fa fa-envelope fa-3x"></i>
                    <strong class="h2"><spring:message code="form.user.email"/>: </strong>
                    <span class="h3"><c:out value="${user.email}"/></span>
                </div>
                <div class="well">
                    <i class="fa fa-users fa-3x"></i>
                    <strong class="h2"><spring:message code="form.user.permission"/>: </strong>
                    <span class="h3"><c:out value="${user.permission}"/></span>
                </div>
                <br>
                <a class="btn btn-primary" href="<spring:url value="/user/edit/${user.id}"/>"><spring:message
                        code="pages.editUser"/></a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger"><spring:message code="error.userNotExist"/></div>
        </c:otherwise>
    </c:choose>
</div>