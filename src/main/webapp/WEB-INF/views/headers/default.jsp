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
                style="color: white; display: inline-block; font-size: 100%; margin: 0 auto;">SiorVen</h2>
        <h3 style="display: inline-block; font-size: 80%; margin: 0 auto;">
            <spring:message code="global.subtitle"/>
        </h3>
    </a>
</div>
<!-- /.navbar-header -->

<ul class="nav navbar-top-links navbar-right">
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
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal" var="principal"/>
                <c:set value="${principal.username}" var="username"/>
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
                    <spring:message code="role.ROLE_ADMIN" var="role"/>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
                    <spring:message code="role.ROLE_ADMIN" var="role"/>
                </sec:authorize>
                <sec:authorize access="!hasAnyAuthority({'ROLE_ADMIN', 'ROLE_REP'})">
                    <spring:message code="role.unknown" var="role"/>
                </sec:authorize>
                <li>
                    <a><spring:message code="msg.wellcome" arguments="${username},${role}"/></a>
                </li>
                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                    <li>
                        <a href="<c:url value="/user/manager" />">
                            <i class="fa fa-users fa-fw"></i>
                            <spring:message code="action.manageUsers"/>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/machine/manager" />">
                            <i class="fa fa-building fa-fw"></i>
                            <spring:message code="action.manageMachines"/>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/suggestion/config" />">
                            <i class="fa fa-gear fa-fw"></i>
                            <spring:message code="pages.suggestionConfig"/>
                        </a>
                    </li>
                </sec:authorize>
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