<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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

