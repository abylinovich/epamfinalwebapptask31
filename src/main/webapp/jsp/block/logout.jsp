<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Logout" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-2">
            <section id="content">
                <form action="">
                    <div>
                        <input type="submit" value="Back home">
                    </div>
                </form>
            </section>
        </div>

        <div class="col-sm-8">
        </div>

        <div class="col-sm-2">
            <section id="content">
                <form action="">
                    <div>
                        <input type="submit" value="Log out">
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
