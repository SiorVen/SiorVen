<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
        <span class="icon-bar"></span> <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<c:url value="/" />">
        <img style="height: 130%; display: inline-block;" alt="Siorven logo"
             src="<c:url value="/res/img/siorven.png"/>">
        <h2
                style="color: white; display: inline-block; font-size: 100%; margin: 0px auto;">SiorVen</h2>
        <h3 style="display: inline-block; font-size: 80%; margin: 0px auto;">
            <spring:message code="global.subtitle"/>
        </h3>
    </a>
</div>
<!-- /.navbar-header -->

<ul class="nav navbar-top-links navbar-right">
    <li class="dropdown"><a class="dropdown-toggle"
                            data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
        <i class="fa fa-caret-down"></i>
    </a>
    <li class="dropdown"><a class="dropdown-toggle"
                            data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i> <i
            class="fa fa-caret-down"></i>
    </a>
        <ul class="dropdown-menu dropdown-user">
            <sec:authorize access="!isAuthenticated()">
                <li>
                    <a href="<c:url value="/login"/>">
                        <i class="fa fa-sign-in fa-fw"></i>
                        <spring:message code="action.login"/>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/user/register" />">
                        <i class="fa fa-pencil-square-o fa-fw"></i>
                        <spring:message code="action.registerUrserf"/>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal" var="principal"/>
                <c:set value="${principal.username}" var="username"/>
                <c:set value="${principal.email}" var="email"/>
                <li>
                    <a><spring:message code="msg.wellcome" arguments="${username},${email}"/></a>
                </li>
                <li>
                    <a href="#" onclick="$('#logout-form').submit()"><i
                            class="fa fa-sign-out fa-fw"></i><spring:message
                            code="action.logout"/></a>
                    <c:url var="logoutUrl" value="/logout"/>
                    <form:form id="logout-form" action="${logoutUrl}" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form:form>
                </li>
            </sec:authorize>
        </ul> <!-- /.dropdown-user --></li>
</ul>
<!-- /.navbar-top-links -->