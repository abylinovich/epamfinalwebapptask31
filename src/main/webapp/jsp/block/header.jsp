<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="url" value="${param.url}" />

<script src="../../resources/js/libs/bootstrap.min.js"></script>
<link href="../../resources/css/libs/bootstrap.min.css" rel="stylesheet" type="text/css" media="all"/>
<link href="../../resources/css/style.css" rel="stylesheet" type="text/css" media="all"/>

<header class="header">
    <a href="action?command=changeLang&language=en&url=${url}">EN</a>
    <a href="action?command=changeLang&language=ru&url=${url}">RU</a>
</header>
