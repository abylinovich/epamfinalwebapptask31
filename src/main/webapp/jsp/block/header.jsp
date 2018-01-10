<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:set var="user" value="${sessionScope.user}" scope="session" />


<script src="../../resources/js/validation.js"></script>
<script src="../../resources/js/libs/bootstrap.min.js"></script>
<link href="../../resources/css/libs/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
<link href="../../resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>

<header>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <%@include file="language-menu.jsp"%>
            </div>
            <div class="col-sm-2">
                <%@include file="question-menu.jsp"%>
            </div>
            <div class="col-sm-2">
            </div>
            <div class="col-sm-2">
            </div>
            <div class="col-sm-2">
                <%@include file="greetings.jsp"%>
            </div>
            <div class="col-sm-2">
                <%@include file="logout-menu.jsp"%>
            </div>
        </div>
    </div>
</header>
