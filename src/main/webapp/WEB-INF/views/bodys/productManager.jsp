<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="well">
    <span class="h1">
        <c:message code="pages.productManager"/>
    </span>
</div>
<jstl:if test="${message != null}">
    <div class="alert alert-info alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${message}
    </div>
</jstl:if>
<a type="button" class="btn btn-primary" style="margin: 20px" href="<c:url value="/product/register"/>">
    <c:message code="action.createProduct"/>
</a>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="usertable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th></th>
            <th><c:message code="form.product.name"/></th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript" src="<c:url value="/res/js/jquery.dataTables.min.js"/>"></script>
<script>
    $(document)
        .ready(
            function () {

                $('#usertable')
                    .dataTable(
                        {
                            language: {
                                url: '../res/json/datatables.json'
                            },
                            "ajax": {
                                "url": "../api/product/datatable",
                                "type": "POST"
                            },
                            "columnDefs": [{
                                "targets": 0,
                                "orderable": false
                            },
                                {"width": "120px", "targets": 0}],
                            "columns": [
                                {
                                    "data": "id",
                                    "render": function (data, type, full, meta) {
                                        return '<div class="btn-group" style="width: "><a class="btn btn-primary" href="../product/' + data + '"><i class="fa fa-user"></i></a>' +
                                            '<a class="btn btn-danger" href="../product/delete/' + data + '"><i class="fa fa-times"></i></a></div>';
                                    }
                                }, {
                                    "data": "name"
                                }]
                        });
            });
</script>