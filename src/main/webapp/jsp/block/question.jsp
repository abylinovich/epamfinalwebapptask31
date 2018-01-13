<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Question" scope="session" />

<div class="container">
    <section id="content" style="width: 100%">
        <h3>
            <fmt:message key="theme" />
            ${question.theme.title}
        </h3>
        <h1>
            <a href="/action?command=question&do=get&id=${question.questionId}">${question.title}</a>
        </h1>
        <h2>
            <fmt:message key="question" />
            ${question.question}
        </h2>
        <h3>
            <fmt:message key="author" />
            <a href="/action?command=user&id=${question.user.userId}">${question.user.username}</a>
        </h3>
    </section>
</div>
