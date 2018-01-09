<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
        LOGIN PAGE
    </jsp:attribute>
    <jsp:body>

        <div style="width: 300px">
            <c:url value="/j_spring_security_check" var="loginUrl"/>
            <form action="${loginUrl}" method="post">
                <h2 class="form-signin-heading">Please sign in</h2>
                <input class="form-control" name="j_username" placeholder="Email address" required autofocus >
                <input type="password" class="form-control" name="j_password" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block">SIGN IN</button>
            </form>
        </div>
        <div class="do-registration-msg-wrapper">
            <div class="do-registration-msg">
                <span> New to CHAT? </span>
                <%--<span>Please do <a class="registerLink" href="/chat-server-application/registration">Registration</a></span>--%>
                <span>Please do <a class="registerLink" href="/registration">Registration</a></span>
            </div>
        </div>
    </jsp:body>
</t:layout>

