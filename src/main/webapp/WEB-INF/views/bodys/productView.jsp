<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="jumbotron">
    <span class="h1">
        <c:message code="pages.productView"/>: <jstl:out value="${product.name}"/>
    </span>
</div>
<jstl:if test="${message != null}">
    <div class="alert alert-info alert-dismissable">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${message}
    </div>
</jstl:if>
<div class="well" >
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<form:form action="${pageContext.request.contextPath}/product/${product.id}/ingredients/add" commandName="ingredientForm">
    <label for="resource"><c:message code="form.ingredient.resource"/> </label><form:input path="name" name="name"
                                                                                           id="resource"
                                                                                           type="text"/>
    <label for="qty"><c:message code="form.ingredient.qty"/> </label><form:input path="qty" name="qty" id="qty"
                                                                                 type="text"/>
    <button type="submit" class="btn btn-primary" style="margin: 20px">
        <c:message code="action.addIngredient"/>
    </button>
</form:form>
</div>
<div class="well">
    <span><c:message code="suggest.newResource"/></span>
    <a type="button" class="btn btn-primary" href="<c:url value="/resource/register?prodid=${product.id}"/>"><c:message
            code="action.crateResource"/></a>
</div>
<div class="container-fluid" style="overflow-x: scroll">
    <table id="usertable"
           class="table table-striped table-bordered table-hover table-responsive"
           style="width: 100%;">
        <thead>
        <tr>
            <th></th>
            <th><c:message code="form.ingredient.name"/></th>
            <th><c:message code="form.ingredient.qty"/></th>
            <th><c:message code="form.ingredient.unit"/></th>
        </tr>
        </thead>
    </table>
</div>
<script>
    <jstl:set var="id" value="${product.id}"/>
    var idVar = '<jstl:out value="${id}"/>';
    $(document)
        .ready(
            function () {
                <c:message var="noResult" code="resource.noResult"/>
                var noResult = '<jstl:out value="${noResult}"/>';
                var path = $("#path").val();
                $("#resource").autocomplete({
                    source: path + "/api/resource/search",
                    noResult: noResult
                });
                $('#usertable')
                    .dataTable(
                        {
                            language: {
                                url: path + '/res/json/datatables.json'
                            },
                            "ajax": {
                                "url": path + "/api/product/" + idVar + "/ingredients/datatable",
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
                                        return '<div class="btn-group" style="width: ">' +
                                            '<a class="btn btn-danger" href="' + path + '/ingredient/delete/' + data + '"><i class="fa fa-times"></i></a></div>';
                                    }
                                }, {
                                    "data": "name"
                                }, {
                                    "data": "qty"
                                }, {
                                    "data": "unit"
                                }]
                        });
            });


</script>