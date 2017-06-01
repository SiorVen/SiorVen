<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <span class="h1" >
        <c:message code="pages.userManager" />
    </span>
</div>
<jstl:if test="${message != null}">
    <div class="alert alert-info alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${message}
    </div>
</jstl:if>
<a type="button" class="btn btn-primary" style="margin: 20px" href="<c:url value="/user/register"/>">
    <c:message code="action.createUser"/>
</a>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="usertable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th></th>
            <th><c:message code="form.user.permission"/></th>
            <th><c:message code="form.user.username"/></th>
            <th><c:message code="form.user.email"/></th>
        </tr>
        </thead>
    </table>
</div>
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<script>
    $(document)
        .ready(
            function () {
                var path = $('#path').val();
                $('#usertable')
                    .dataTable(
                        {
                            language: {
                                url: path + '/res/json/datatables.json'
                            },
                            "ajax": {
                                "url": path + "/api/user/datatable",
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
                                        return '<div class="btn-group" style="width: "><a class="btn btn-primary" href="' + path + '/user/' + data + '"><i class="fa fa-user"></i></a>' +
                                            '<a class="btn btn-warning" href="' + path + '/user/edit/' + data + '"><i class="fa fa-pencil-square-o"></i></a>' +
                                            '<a class="btn btn-danger" href="' + path + '/user/delete/' + data + '"><i class="fa fa-times"></i></a></div>';
                                    }
                                }, {
                                    "data": "type"
                                }, {
                                    "data": "username"
                                }, {
                                    "data": "email"
                                }]
                        });
            });
</script>