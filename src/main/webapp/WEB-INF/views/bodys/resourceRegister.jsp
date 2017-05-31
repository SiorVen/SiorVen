<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
        <sf:form action="${pageContext.request.contextPath}/resource/register" method="post" commandName="resource">
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.register" /></legend>
                </div>
                <div class="control-group">
                    <!-- Username -->
                    <label class="control-label" for="name"><i class="fa fa-user" aria-hidden="true"></i>
                        <s:message code="form.resource.name"/></label>
                    <div class="controls">
                        <sf:input path="name" type="text" cssClass="form-control" id="name"/>
                        <sf:errors path="name" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- Permiso -->
                    <label class="control-label" for="resourceType"><i class="fa fa-users" aria-hidden="true"></i> <s:message
                            code="form.resource.resourceType"/></label>
                    <div class="controls">
                        <sf:select path="resourceType" id="resourceType" items="${resourceType}" cssClass="form-control"/>
                        <sf:errors path="resourceType" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 20px">
                    <!-- Button -->
                    <div class="controls">
                        <button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>
                            <s:message code="form.resource.register"/>
                        </button>
                    </div>
                </div>
            </fieldset>
        </sf:form>
    </div>
</div>