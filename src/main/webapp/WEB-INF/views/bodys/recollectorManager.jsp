<%--
  Created by IntelliJ IDEA.
  User: joseb
  Date: 31/05/2017
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="well">
    <input type="hidden" id="path" value="${pageContext.request.contextPath}">
    <span class="h1">
        <c:message code="pages.recollectorManager"/>
    </span>
</div>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="machinetable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th><c:message code="recollector.link"/></th>
            <th><c:message code="recollector.alias"/></th>
            <th><c:message code="recollector.conection"/></th>
            <th><c:message code="recollector.machine"/></th> <!-- To string -->
        </tr>
        </thead>
    </table>
</div>
</div>
<script>
    $(document)
        .ready(
            function () {
                <c:message var="noResult" code="resource.noResult"/>
                var noResult = '<jstl:out value="${noResult}"/>';
                var machineId = '<jstl:out value="${machineId}"/>';
                var csfrKey = '<jstl:out value="${_csrf.parameterName}"/>';
                var csfrToken = '<jstl:out value="${_csrf.token}"/>';
                var path = $("#path").val();
                $('#machinetable')
                    .dataTable(
                        {
                            language: {
                                url: path + '/res/json/datatables.json'
                            },
                            "ajax": {
                                "url": path + "/api/recollector/datatable/",
                                "type": "POST"
                            },
                            "columns": [
                                {
                                    "data": "alias",
                                    "render": function (data, type, full, meta) {
                                        return ' <form action="' + path + '/recollector/link" method="post">' +
                                            '<input type="hidden" name="recollectorAlias" value="' + data + '"/>' +
                                            '<input type="hidden" name="machineId" value="' + machineId + '"/>' +
                                            '<input type="hidden" name="' + csfrKey + '" value="' + csfrToken + '"/>' +
                                            '<button type="submit" class="btn btn-primary" name="b" value="" >' +
                                            '<i class="fa fa-link"></i></button></form> ';
                                    }
                                }, {
                                    "data": "alias"
                                }, {
                                    "data": "connection"
                                }, {
                                    "data": "machineName"
                                }]
                        });
            });
</script>