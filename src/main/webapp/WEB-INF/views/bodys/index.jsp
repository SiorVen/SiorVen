<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid" >
    <div class="jumbotron">
        <jstl:if test="${message != null}">
            <div class="alert alert-info alert-dismissable">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    ${message}
            </div>
        </jstl:if>
        <div class="jumbotron">
            <div class="h1"><s:message code="global.wellcome" /></div>
        </div>
        <hr />
        <p class="h2"><s:message code="global.reference" /></p>
    </div>
</div>
