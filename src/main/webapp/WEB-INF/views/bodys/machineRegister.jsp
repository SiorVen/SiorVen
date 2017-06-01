<%--
  Created by IntelliJ IDEA.
  User: Andoni
  Date: 30/05/2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
        <sf:form action="${pageContext.request.contextPath}/machine/register" method="post"
                 commandName="machineModelRegister">
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.machineRegister"/></legend>
                </div>
                <div class="control-group">
                    <div class="control-group">
                        <!-- Modelo -->
                        <label class="control-label" for="machineModelId"><i class="fa fa-users" aria-hidden="true"></i>
                            <s:message
                                    code="form.machine.modelFile"/></label>
                        <div class="controls">
                            <sf:select path="machineModelId" id="machineModelId" items="${models}"
                                       cssClass="form-control"/>
                            <sf:errors path="machineModelId" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- alias -->
                        <label class="control-label" for="alias"><i class="fa fa-user" aria-hidden="true"></i>
                            <s:message code="form.machine.alias"/></label>
                        <div class="controls">
                            <sf:input path="alias" type="text" cssClass="form-control" id="alias"/>
                            <sf:errors path="alias" cssClass="text-danger"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <!-- id -->
                        <div class="controls">
                            <sf:input path="id" type="hidden" cssClass="form-control" id="reference"/>
                            <sf:errors path="id" cssClass="text-danger"/>
                        </div>
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
