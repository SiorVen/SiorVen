<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse"
            data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
        <span class="icon-bar"></span> <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" action="index" namespace="/">
        <img style="height: 130%; display: inline-block;" alt="Siorven logo"
             src="<c:url value="/res/img/siorven.png"/>">
        <h2
                style="color: white; display: inline-block; font-size: 100%; margin: 0px auto;">SiorVen</h2>
        <h3 style="display: inline-block; font-size: 80%; margin: 0px auto;">
            SOY UN SUBTITULO
        </h3>
    </a>
</div>
<!-- /.navbar-header -->

<ul class="nav navbar-top-links navbar-right">
    <li class="dropdown"><a class="dropdown-toggle"
                            data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
        <i class="fa fa-caret-down"></i>
    </a>
    <li class="dropdown"><a class="dropdown-toggle"
                            data-toggle="dropdown" href="#"><i class="fa fa-user fa-fw"></i> <i
            class="fa fa-caret-down"></i>
    </a>
        <ul class="dropdown-menu dropdown-user">
            <!-- TODO: Mostrar nombre de ususario y logout si logueado y login si no-->
        </ul> <!-- /.dropdown-user --></li>
    <!-- /.dropdown -->
    <c:url var="indexEN" value="/">
        <c:param name="request_locale">en</c:param>
    </c:url>
    <c:url var="indexES" value="/">
        <c:param name="request_locale">es</c:param>
    </c:url>
    <c:url var="indexEU" value="/">
        <c:param name="request_locale">is</c:param>
    </c:url>

    <li class="dropdown"><a class="dropdown-toggle"
                            data-toggle="dropdown" href="#"> <i class="fa fa-globe fa-fw"></i>
        <i class="fa fa-caret-down"></i>
    </a>
        <ul class="dropdown-menu dropdown" id="alertsList">
            <li><a href="${indexEN}">
                <img height="20" width="40" alt="English"
                     src='<c:url value="/res/img/english.png"/>'>
                English
            </a></li>
            <li><a href="${indexES}">
                <img height="20" width="40" alt="Español"
                     src='<c:url value="/res/img/spanish.png"/>'>
                Castellano
            </a></li>
            <li><a href="${indexEU}">
                <img height="20" width="40" alt="Euskara"
                     src='<c:url value="/res/img/basque.png"/>'>
                Euskara
            </a></li>
        </ul>
    </li>
</ul>
<!-- /.navbar-top-links -->