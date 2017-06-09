<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 05/06/2017
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
        <sf:form action="${pageContext.request.contextPath}/reposition/register" method="post"
                 commandName="machineResourceAdd">
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.repositionAdd"/></legend>
                </div>
                <div class="control-group">
                    <div class="control-group">
                        <!-- name -->
                        <label class="control-label" for="productAdd"><i class="fa fa-user" aria-hidden="true"></i>
                            <s:message code="pages.productView"/></label>
                        <div class="controls">
                            <sf:input path="product" type="text" cssClass="form-control" id="productAdd"/>
                            <sf:errors path="product" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- quantity -->
                        <label class="control-label" for="quantityAdd"><i class="fa fa-user" aria-hidden="true"></i>
                            <s:message code="form.ingredient.qty"/></label>
                        <div class="controls">
                            <sf:input path="quantity" type="text" cssClass="form-control" id="quantityAdd"/>
                            <sf:errors path="quantity" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- slot -->
                        <label class="control-label" for="machineSlotIdAdd"><i class="fa fa-users"
                                                                               aria-hidden="true"></i>
                            <s:message
                                    code="form.reposition.slot"/></label>
                        <div class="controls">
                            <sf:select path="machineSlotId" id="machineSlotIdAdd" items="${slots}"
                                       cssClass="form-control"/>
                            <sf:errors path="machineSlotId" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- id -->
                        <div class="controls">
                            <sf:input path="id" type="hidden" cssClass="form-control" id="reference"/>
                            <sf:errors path="id" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="control-group" style="margin-top: 20px">
                        <!-- Button -->
                        <div class="controls">
                            <button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>
                                <s:message code="form.user.register"/>
                            </button>
                        </div>
                    </div>
                </div>
            </fieldset>
        </sf:form>
    </div>
</div>
<input type="hidden" id="path" value="${pageContext.request.contextPath}">
<script>
    $(document)
        .ready(
            function () {
                var noResult = '<c:out value="${noResult}"/>';
                var path = $("#path").val();
                $("#productAdd").autocomplete({
                    source: path + "/api/reposition/search",
                    noResult: noResult
                });
            });
</script>
