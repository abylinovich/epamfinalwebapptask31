<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <section id="content">
        <form name="questionForm" action="/action" method="post">
            <input type="hidden" name="command" value="question" />
            <h2>
                <fmt:message key="ask" />
            </h2>
            <div class="form-group">
                <select class="jsSelectTheme" name="theme">
                    <option value="" disabled selected hidden>
                        <fmt:message key="selectTheme" />
                    </option>
                    <c:forEach items="${themes}" var="theme">
                        <option value="${theme.themeId}">${theme.title}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="alert alert-danger jsSelectThemeError" role="alert" style="display: none">
                <fmt:message key="selectTheme.error" />
            </div>
            <div class="form-group">
                <input type="text" class="form-control jsQuestionTitle" placeholder="<fmt:message key="title" />" name="title">
            </div>
            <div class="alert alert-danger jsQuestionTitleError" role="alert" style="display: none">
                <fmt:message key="title.error" />
            </div>
            <div class="form-group">
                <textarea class="form-control jsQuestion" placeholder="<fmt:message key="question.placeholder" />" name="question" ></textarea>
            </div>
            <div class="alert alert-danger jsQuestionError" role="alert" style="display: none">
                <fmt:message key="question.error" />
            </div>
            <div>
                <input type="submit" class="jsSubmitQuestion" value="<fmt:message key="button.question" />">
            </div>
        </form>
        <div class="alert alert-danger jsSubmitQuestionError" role="alert" style="display: none"><fmt:message key="formSubmit.error" /></div>
    </section>
</div>


