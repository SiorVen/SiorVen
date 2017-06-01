<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 30/05/2017
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <input type="hidden" id="path" value="${pageContext.request.contextPath}">
    <span class="h1">
        <c:message code="pages.suggestionManager"/>
    </span>
</div>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="machinetable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th><c:message code="suggestion.date"/></th>
            <th><c:message code="suggestion.weight"/></th>
            <th><c:message code="suggestion.reason"/></th>
            <th><c:message code="suggestion.consequence"/></th> <!-- To string -->
        </tr>
        </thead>
    </table>
</div>
</div>
<script>
    $(document)
        .ready(
            function () {
                var machineId = '<jstl:out value="${machineId}"/>';
                var path = $("#path").val();
                var emptyMsg = '<c:message code="suggestion.notEnoughSales"/>'
                $('#machinetable')
                    .dataTable(
                        {
                            language: {
                                url: path + '/res/json/datatables.json'
                            },
                            "ajax": {
                                "url": path + "/api/suggestion/datatable/" + machineId,
                                "type": "POST"
                            },
                            "language": {
                                "emptyTable": emptyMsg
                            },
                            "columns": [
                                {
                                    "data": "generateDate"
                                }, {
                                    "data": "weight"
                                }, {
                                    "data": "reason"
                                }, {
                                    "data": "consequence"
                                }]
                        });
            });
</script>

