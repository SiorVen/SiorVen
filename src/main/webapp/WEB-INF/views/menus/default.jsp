<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <sec:authorize access="isAuthenticated()">
                <li>
                    <sec:authentication property="principal" var="principal"/>
                    <a href="<c:url value="/user/${principal.id}" />">
                        <i class="fa fa-user fa-fw"></i>
                        <spring:message code="action.myProfile"/>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <li>
                    <a href="<c:url value="/user/manager" />">
                        <i class="fa fa-users fa-fw"></i>
                        <spring:message code="action.manageUsers"/>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <li>
                    <a href="#">
                        <i class="fa fa-unlock-alt fa-fw"></i>
                        <spring:message code="msg.loginToUnlock"/>
                    </a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->
