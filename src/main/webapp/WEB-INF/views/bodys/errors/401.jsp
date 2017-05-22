<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="jumbotron">
        <div class="well">
            <h1>401</h1>
            <h2>T.T <c:message code="error.401"/> T.T</h2>
            <p><c:message code="error.401.descr"/></p>
            <jstl:if test="${reason != null}">
                <div class="alert alert-info"><jstl:out value="${reason}" /></div>
            </jstl:if>
        </div>
        <img class="img-responsive center-block" alt="Error 401 Image" src="<c:url value="/res/img/401.png" />">
    </div>
</div>