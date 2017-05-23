<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="jumbotron">
        <div class="well">
            <h1>500</h1>
            <h2>T.T <c:message code="error.500"/> T.T</h2>
            <p><c:message code="error.500.descr"/></p>
        </div>
        <img class="img-responsive center-block" alt="Error 500 Image" src="<c:url value="/res/img/500.jpg" />">
    </div>
</div>