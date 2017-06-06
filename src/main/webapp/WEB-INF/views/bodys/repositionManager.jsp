<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="bs-example bs-example-tabs">
    <ul class="nav nav-tabs tabs" id="myTab">
        <li class="col-xs-6 col-md-4 col-lg-4 active"><a href="#reposition" data-toggle="tab"><s:message code="page.tab.reposition"/></a></li>
        <li class="col-xs-6 col-md-4 col-lg-4"><a href="#suggestion" data-toggle="tab"><s:message code="page.tab.suggestion"/></a></li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade in active" id="reposition">
            <jsp:include page="repositionDatatable.jsp"/>
        </div>
        <div class="tab-pane fade" id="suggestion">
            <jsp:include page="suggestionManager.jsp"/>
        </div>
    </div>
</div>