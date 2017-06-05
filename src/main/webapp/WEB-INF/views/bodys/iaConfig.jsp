<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container-fluid">
    <div class="jumbotron col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
        <sf:form action="${pageContext.request.contextPath}/suggestion/config" method="post" commandName="conf">
            <div class="alert alert-dismissable alert-warning">
                <s:message code="message.config.NoEffectTillNextDay"/>
            </div>
            <fieldset>
                <div id="legend">
                    <legend class=""><s:message code="pages.suggestionConfig"/></legend>
                </div>
                <div class="control-group">
                    <!-- asociationUsedDays -->
                    <label class="control-label" for="asociationUsedDays"><i class="fa fa-calendar" aria-hidden="true"></i>
                        <s:message code="form.confParam.asociationUsedDays"/></label>
                    <div class="controls">
                        <sf:input path="asociationUsedDays" type="text" cssClass="form-control" id="asociationUsedDays"/>
                        <sf:errors path="asociationUsedDays" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- successSaleNo -->
                    <label class="control-label" for="successSaleNo"><i class="fa fa-star" aria-hidden="true"></i>
                        <s:message code="form.confParam.successSaleNo"/></label>
                    <div class="controls">
                        <sf:input path="successSaleNo" type="text" cssClass="form-control" id="successSaleNo"/>
                        <sf:errors path="successSaleNo" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- minMaxPeriod -->
                    <label class="control-label" for="minMaxPeriod"><i class="fa fa-calendar" aria-hidden="true"></i>
                        <s:message
                                code="form.confParam.minMaxPeriod"/></label>
                    <div class="controls">
                        <sf:input path="minMaxPeriod" id="minMaxPeriod" type="text" cssClass="form-control"/>
                        <sf:errors path="minMaxPeriod" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- ratioMin -->
                    <label class="control-label" for="ratioMin"><i class="fa fa-minus" aria-hidden="true"></i>
                        <s:message
                                code="form.confParam.ratioMin"/></label>
                    <div class="controls">
                        <sf:input path="ratioMin" id="ratioMin" type="text" cssClass="form-control"/>
                        <sf:errors path="ratioMin" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group">
                    <!-- ratioMax -->
                    <label class="control-label" for="ratioMax"><i class="fa fa-plus" aria-hidden="true"></i>
                        <s:message
                                code="form.confParam.ratioMax"/></label>
                    <div class="controls">
                        <sf:input path="ratioMax" id="ratioMax" type="text" cssClass="form-control"/>
                        <sf:errors path="ratioMax" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="control-group" style="margin-top: 20px">
                    <!-- Button -->
                    <div class="controls">
                        <button type="submit" class="btn btn-success"><i class="fa fa-save" aria-hidden="true"></i>
                            <s:message code="form.save"/>
                        </button>
                    </div>
                </div>
            </fieldset>
        </sf:form>
    </div>
</div>