<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 25/05/2017
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
        <sf:form action="${pageContext.request.contextPath}/machine/register" method="post" commandName="machine" enctype="multipart/form-data">
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.machineRegister" /></legend>
                </div>
                <div class="control-group">
                    <!-- Alias -->
                    <label class="control-label" for="alias"><i class="fa fa-building" aria-hidden="true"></i>
                        <s:message code="form.machine.alias"/></label>
                    <div class="controls">
                        <sf:input path="alias" type="text" cssClass="form-control" id="alias"/>
                        <sf:errors path="alias" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- Model -->
                    <label class="control-label" for="alias"><i class="fa fa-file-code-o" aria-hidden="true"></i>
                        <s:message code="form.machine.modelFile"/></label>
                        <div class="controls">
                            <sf:input path="model" type="file" cssClass="form-control" id="machineModel"/>
                            <sf:errors path="model" cssClass="text-danger"/>
                        </div>
                </div>
                <div class="control-group">
                    <!-- Manufacturer -->
                    <label class="control-label" for="alias"><i class="fa fa-file-code-o" aria-hidden="true"></i>
                        <s:message code="form.machine.modelManufacturer"/></label>
                    <div class="controls">
                        <sf:input path="machineModel.manufacturer" type="text" cssClass="form-control" id="machineModelManufacturer"/>
                        <sf:errors path="machineModel.manufacturer" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- Manufacturer -->
                    <label class="control-label" for="alias"><i class="fa fa-file-code-o" aria-hidden="true"></i>
                        <s:message code="form.machine.modelDescription"/></label>
                    <div class="controls">
                        <sf:input path="machineModel.description" type="text" cssClass="form-control" id="machineModelDescription"/>
                        <sf:errors path="machineModel.description" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 20px">
                    <!-- Button -->
                    <div class="controls">
                        <button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>
                            <s:message code="form.machine.register"/>
                        </button>
                    </div>
                </div>
            </fieldset>
        </sf:form>
    </div>
</div>
