<c:if test="${sessionScope.user.userId eq question.user.userId or sessionScope.user.role eq 'ADMIN'}">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4">
            </div>

            <div class="col-sm-6">
                <div class="container">
                    <section class="content">
                        <div class="jsEditBarContainer">
                            <button type="button" class="btn button-lang jsEditQuestionShow"><fmt:message key="edit" bundle="${ques}" /></button>
                            <form name="deleteForm" action="/action" method="post">
                                <input type="hidden" name="command" value="question" />
                                <input type="hidden" name="do" value="delete" />
                                <input type="hidden" name="id" value="${question.questionId}" />
                                <button type="submit" class="btn button-lang jsDeleteQuestionSubmit"><fmt:message key="delete" bundle="${ques}" /></button>
                            </form>
                        </div>
                        <div class="jsEditQuestionContainer" style="display: none">
                            <form name="editForm" action="/action" method="post" >
                                <input type="hidden" name="command" value="question" />
                                <input type="hidden" name="do" value="edit" />
                                <input type="hidden" name="id" value="${question.questionId}" />
                                <%@include file="question-data-input.jsp"%>
                                <button type="submit" class="btn button-lang jsEditQuestionSubmit"><fmt:message key="submit" bundle="${ques}" /></button>
                                <div class="alert alert-danger jsSubmitQuestionError" role="alert" style="display: none"><fmt:message key="formSubmit.error" /></div>
                                <button type="button" class="btn button-lang jsEditQuestionHide"><fmt:message key="hide" bundle="${ques}" /></button>
                            </form>
                        </div>
                    </section>
                </div>

            </div>
            <div class="col-sm-2">
            </div>
        </div>
    </div>
</c:if>