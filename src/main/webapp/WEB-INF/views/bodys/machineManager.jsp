<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 29/05/2017
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <span class="h1" >
        <c:message code="pages.mmachineManager" />
    </span>
</div>
<a type="button" class="btn btn-primary" style="margin: 20px" href="<c:url value="/machine/register"/>">
    <c:message code="action.createMachine"/>
</a>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="machinetable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th></th>
            <th><c:message code="form.machine.alias"/></th>
            <th><c:message code="form.machine.modelFile"/></th>
            <th><c:message code="form.machine.modelManufacturer"/></th>
        </tr>
        </thead>
    </table>
</div>

<script>
    $(document)
        .ready(
            function () {

                $('#machinetable')
                    .dataTable(
                        {
                            language: {
                                url: '../res/json/datatables.json'
                            },
                            "ajax": {
                                "url": "../api/machine/datatable",
                                "type": "POST"
                            },
                            "columnDefs": [{
                                "targets": 0,
                                "orderable": false
                            },
                                {"width": "150px", "targets": 0}],
                            "columns": [
                                {
                                    "data": "id",
                                    "render": function (data, type, full, meta) {
                                        return '<div class="btn-group" style="width: "><a class="btn btn-primary" href="../machine/' + data + '"><i class="fa fa-building"></i></a>' +
                                            '<a class="btn btn-warning" href="../machine/edit/' + data + '"><i class="fa fa-pencil-square-o"></i></a>' +
                                            '<a class="btn btn-warning" href="../suggestion/manager/' + data + '"><i class="fa fa-pencil-square-o"></i></a>';
                                    }
                                }, {
                                    "data": "alias"
                                }, {
                                    "data": "modelRef"
                                }, {
                                    "data": "manufacturer"
                                }]
                        });
            });
</script>