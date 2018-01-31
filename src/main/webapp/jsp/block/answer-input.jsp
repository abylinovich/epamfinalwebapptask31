<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Answer" scope="session" />


<div class="container">
    <section id="content">
        <form name="answerForm" action="/action" method="post">
            <input type="hidden" name="command" value="answer" />
            <input type="hidden" name="questionId" value="${question.questionId}" />
            <div class="form-group">
                <textarea class="form-control jsAnswer" placeholder="<fmt:message key="answer.placeholder" />" name="answer" ></textarea>
            </div>
            <div class="alert alert-danger jsAnswerError" role="alert" style="display: none">
                <fmt:message key="answer.error" />
            </div>
            <div>
                <input type="submit" class="jsSubmitAnswer" value="<fmt:message key="button.answer" />">
            </div>
        </form>
        <div class="alert alert-danger jsSubmitAnswerError" role="alert" style="display: none"><fmt:message key="formSubmit.error" /></div>
    </section>
</div>


