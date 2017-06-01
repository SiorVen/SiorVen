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
        <sf:form action="${pageContext.request.contextPath}/model/register" method="post" commandName="machineModel"
                 enctype="multipart/form-data">
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.modelRegister"/></legend>
                </div>
                <div class="alert alert-danger"><s:message code="fileupload.maxsizeWarning"/></div>
                <div class="control-group">
                    <!-- Model -->
                    <label class="control-label" for="file"><i class="fa fa-file-code-o" aria-hidden="true"></i>
                        <s:message code="form.machine.modelFile"/></label>
                        <sf:input path="file" type="file" cssClass="form-control file" id="file"
                                  cssStyle="padding-bottom: 40px;"/>
                        <sf:errors path="file" cssClass="text-danger"/>

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
