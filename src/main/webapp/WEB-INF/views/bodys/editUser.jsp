<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
        <sf:form action="${pageContext.request.contextPath}/user/edit" method="post" commandName="user">
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.editUser" /></legend>
                </div>
                <div class="control-group">
                    <!-- Username -->
                    <label class="control-label" for="username"><i class="fa fa-user" aria-hidden="true"></i>
                        <s:message code="form.user.username"/></label>
                    <div class="controls">
                        <sf:input path="username" type="text" cssClass="form-control" id="username"/>
                        <sf:errors path="username" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- email -->
                    <label class="control-label" for="email"><i class="fa fa-envelope" aria-hidden="true"></i>
                        <s:message code="form.user.email"/></label>
                    <div class="controls">
                        <sf:input path="email" type="text" cssClass="form-control" id="email"/>
                        <sf:errors path="email" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- Permiso -->
                    <label class="control-label" for="permission"><i class="fa fa-users" aria-hidden="true"></i> <s:message
                            code="form.user.permission"/></label>
                    <div class="controls">
                        <sf:select path="permission" id="permission" items="${userTypes}" cssClass="form-control"/>
                        <sf:errors path="permission" cssClass="text-danger"/>
                    </div>
                </div>
                <sf:input path="id" type="hidden" />
                <div class="control-group" style="margin-top: 20px">
                    <!-- Button -->
                    <div class="controls">
                        <button type="submit" class="btn btn-success"><i class="fa fa-plus" aria-hidden="true"></i>
                            <s:message code="form.user.edit"/>
                        </button>
                    </div>
                </div>
            </fieldset>
        </sf:form>
    </div>
</div>