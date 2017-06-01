<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8"/>
    <!-- Tell the browser to be responsive to screen width -->

    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel='shortcut icon' type='image/png'
          href="<c:url value='/res/img/favicon.png'/>"/>

    <tiles:importAttribute name="stylesheets" ignore="true"/>

    <!-- <script type="text/javascript" src="<c:url value='/res/js/jquery-3.2.1.min.js' />"></script> -->
    <!-- Load jQuery UI CSS  -->
    <!-- TODO hacer estas librerias locales -->

    <%--<link rel="stylesheet" href="<c:url value='/res/css/jquery-ui.css'/>"/>--%>
    <link rel="stylesheet" media="all" type="text/css"
          href="<c:url value='/res/css/jquery-ui.css'/>"/>
    <!-- Load jQuery JS -->
    <script src="<c:url value='/res/js/jquery-1.9.1.js'/>"></script>
    <!-- Load jQuery UI Main JS  -->
    <script src="<c:url value='/res/js/jquery-ui.js'/>"></script>
    <script type="text/javascript"
            src="<c:url value='/res/js/bootstrap.min.js'/>"></script>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/res/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/res/css/font-awesome.min.css'/>"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/res/css/metisMenu.min.css'/>"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/res/css/sb-admin-2.min.css'/>"/>

    <script type="text/javascript"
            src="<c:url value='/res/js/metisMenu.min.js'/>"></script>
    <script type="text/javascript"
            src="<c:url value='/res/js/sb-admin-2.min.js'/>"></script>
    <link href="<c:url value='/res/css/animate.css'/>" rel="stylesheet">
    <script src="<c:url value='/res/js/bootstrap-notify.min.js'/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/res/css/datatables.min.css'/>"/>

    <script type="text/javascript" src="<c:url value='/res/js/datatables.min.js'/>"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <tiles:importAttribute name="title"/>
    <title>SiorVen - <spring:message code="${title}" text="Título"/></title>
</head>

<div id="wrapper" class="fill">
    <nav style="background-color: black; margin: 0 0 0;"
         class="navbar navbar-default navbar-static-top">
        <tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="sidemenu"/>
    </nav>
    <div id="page-wrapper"
         style="display: none; padding-top: 20px; padding-bottom: 10%;" class="fill">
        <tiles:insertAttribute name="body"/>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#page-wrapper').fadeIn(500);
        $('#focused').focus();
    })
</script>
<style>
    .fill {
        min-height: calc(100vh - 51px) !important;
    }
</style>
</body>
</html>