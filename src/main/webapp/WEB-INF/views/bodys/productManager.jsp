<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="well">
    <form:form action="${pageContext.request.contextPath}/product/new" method="post">
        <div class="control-group">
            <label class="control-label" for="name"><i class="fa fa-cart-plus" aria-hidden="true"></i>
                <c:message code="form.product.name"/></label>
            <div class="controls">
                <input type="text" class="form-control" id="name" name="name"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" style="margin: 20px">
            <c:message code="action.createProduct"/>
        </button>
    </form:form>
</div>

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
                                "url": path + "/api/product/datatable",
                                "type": "POST"
                            },
                            "columnDefs": [{
                                "targets": 0,
                                "orderable": false
                            },
                                {"width": "50px", "targets": 0}],
                            "columns": [
                                {
                                    "data": "id",
                                    "render": function (data, type, full, meta) {
                                        return '<div class="btn-group" style="width: "><a class="btn btn-primary" href="' + path + '/product/' + data + '"><i class="fa fa-user"></i></a>' +
                                            '</div>';
                                    }
                                }, {
                                    "data": "name"
                                }]
                        });
            });
</script>