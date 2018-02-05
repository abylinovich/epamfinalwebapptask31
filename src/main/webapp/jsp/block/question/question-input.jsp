<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    <section id="content">
        <form name="questionForm" action="/action" method="post">
            <input type="hidden" name="command" value="question" />
            <input type="hidden" name="do" value="create" />
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
            <%@include file="question-data-input.jsp"%>
            <div>
                <input type="submit" class="jsSubmitQuestion" value="<fmt:message key="button.question" />">
            </div>
        </form>
        <div class="alert alert-danger jsSubmitQuestionError" role="alert" style="display: none"><fmt:message key="formSubmit.error" /></div>
    </section>
</div>


