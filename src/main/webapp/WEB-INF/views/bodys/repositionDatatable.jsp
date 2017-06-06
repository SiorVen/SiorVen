<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 01/06/2017
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <input type="hidden" id="path" value="${pageContext.request.contextPath}">
    <span class="h1">
        <c:message code="pages.repositionManager"/>
    </span>
</div>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="repositiontable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th><c:message code="form.product.data"/></th>
            <th><c:message code="form.product.name"/></th>
            <th><c:message code="form.product.quantity"/></th>
        </tr>
        </thead>
    </table>
</div>
<script>
    $(document)
        .ready(
            function () {
                var machineId = '<jstl:out value="${machineId}"/>';
                var path = $("#path").val();
                $('#repositiontable')
                    .dataTable(
                        {
                            language: {
                                url: path + '/res/json/datatables.json'
                            },
                            "ajax": {
                                "url": path + "/api/reposition/datatable/" + machineId,
                                "type": "POST"
                            },
                            "columns": [
                                {
                                    "data": "id",
                                    "render": function (data, type, full, meta) {
                                        return '<div class="btn-group" style="width: "><a class="btn btn-primary" href="/reposition/edit/' + data + '"><i class="fa fa-building"></i></a>' +
                                            '<a class="btn btn-warning" href="' + path + '/reposition/edit/' + data + '"><i class="fa fa-pencil-square-o"></i></a>' +
                                            '<a class="btn btn-danger" href="' + path + '/reposition/add/' + data + '"><i class="fa fa-exclamation"></i></a>';
                                    }
                                }, {
                                    "data": "name"
                                }, {
                                    "data": "quantity"
                                }]
                        });
            });
</script>
