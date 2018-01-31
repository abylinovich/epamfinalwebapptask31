<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="Pagination" var="pag" scope="session" />


<c:set var="total" value="${sessionScope.totalQuestions}"/>
<c:set var="elementsPerPage" value="${sessionScope.count}" />
<c:set var="pageNumber" value="${sessionScope.page}" />

<c:set var="queryString" value="${pageContext.request.queryString}" />

<c:set var="a">
    <fmt:formatNumber value="${total/elementsPerPage}" maxFractionDigits="0"/>
</c:set>
<c:set var="b" value="${total/elementsPerPage}" />

<c:choose>
    <c:when test="${a==0}">
        <c:set var="numberOfPages" value="1" scope="session"/>
    </c:when>

    <c:when test="${b>a}">
        <c:set var="xxx" value="${b%a}"/>
        <c:if test="${xxx>0}">
            <c:set var="numberOfPages" value="${b-xxx+1}" scope="session"/>
        </c:if>
    </c:when>

    <c:when test="${a>=b}">
        <c:set var="numberOfPages" value="${a}" scope="session"/>
    </c:when>
</c:choose>

<c:set var="start" value="${pageNumber*elementsPerPage-elementsPerPage}"/>
<c:set var="stop" value="${pageNumber*elementsPerPage-1}"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5">
            <h3>
                <fmt:message key="pages" bundle="${pag}" />

                <%--For displaying Previous link --%>
                <c:if test="${pageNumber gt 1}">
                    <a href="/action?${queryString}&page=${pageNumber - 1}"><fmt:message key="previous" bundle="${pag}" /></a>
                </c:if>

                <%--For displaying numered links --%>
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${i != pageNumber}">
                            <a href="/action?${queryString}&page=<c:out value="${i}"/>"><c:out value=" ${i} "/></a>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${i}"/>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <%--For displaying Next link --%>
                <c:if test="${pageNumber lt numberOfPages}">
                    <a href="/action?${queryString}&page=${pageNumber + 1}"><fmt:message key="next" bundle="${pag}" /></a>
                </c:if>
            </h3>
        </div>

        <div class="col-sm-5">
            <h3>
                <fmt:message key="count" bundle="${pag}" />
                <a href="/action?${queryString}&count=3">3</a>
                <a href="/action?${queryString}&count=5">5</a>
                <a href="/action?${queryString}&count=10">10</a>
            </h3>
        </div>
    </div>
</div>

