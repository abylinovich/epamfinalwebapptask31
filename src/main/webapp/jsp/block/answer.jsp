<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Answer" scope="session" />

<div class="container">
    <section id="content" style="width: 100%">
        <h3>
            <a href="/action?command=user&id=${answer.user.userId}">${answer.user.username}</a> : ${answer.answer}
        </h3>
    </section>
</div>
