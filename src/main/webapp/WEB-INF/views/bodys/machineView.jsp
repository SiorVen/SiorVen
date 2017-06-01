<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 30/05/2017
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron">
        <div class="jumbotron">
            <span class="h1"><spring:message code="view.machine"/></span>
        </div>
        <br>
        <div class="well">
            <i class="fa fa-user fa-3x"></i>
            <strong class="h2"><spring:message code="form.machine.alias"/>: </strong>
            <span class="h3"><c:out value="${machineView.alias}"/></span>
        </div>
        <br>
        <div class="well">
            <i class="fa fa-user fa-3x"></i>
            <strong class="h2"><spring:message code="form.machine.modelManufacturer"/>: </strong>
            <span class="h3"><c:out value="${manufacturer}"/></span>
        </div>
        <div class="well">
            <i class="fa fa-user fa-3x"></i>
            <strong class="h2"><spring:message code="form.machine.modelFile"/>: </strong>
            <span class="h3"><c:out value="${reference}"/></span>
        </div>
    </div>
</div>
