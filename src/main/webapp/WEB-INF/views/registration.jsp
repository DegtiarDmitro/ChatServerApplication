<%@ page import="com.chatsystem.server.entity.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<t:layout>
    <jsp:attribute name="title">
        Registration
    </jsp:attribute>
    <jsp:body>
        <c:if test="${confirmationMessage != null}">
            <%--<div class="alert alert-success" role="alert" >${confirmationMessage} Please <a href="/chat-server-application/">Log in</a></div>--%>
            <div class="alert alert-success" role="alert" >${confirmationMessage} Please <a href="/">Log in</a></div>
        </c:if>
        <c:if test="${confirmationMessage == null}">
        <h2>New Buyer Registration</h2>

        <div class="middle-box text-center loginscreen animated fadeInDown">
            <div>

                <hr />

                <h3>Please fill the following fields:</h3>
                <form:form commandName="user" enctype="multipart/form-data"  autocomplete="off" action="/registration"  method="post" class="m-t" data-toggle="validator">
                <%--<form:form commandName="user" enctype="multipart/form-data"  autocomplete="off" action="/chat-server-application/registration"  method="post" class="m-t" data-toggle="validator">--%>
                <c:if test="${alreadyRegisteredMessage != null}">
                    <div class="alert alert-warning" role="alert" >${alreadyRegisteredMessage}</div>
                </c:if>


                    <div class="form-group input-group has-feedback">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-user"></span>
                        </span>
                        <form:input path="username" name="username" placeholder="Login" class="form-control" required="true" />
                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                    </div>

                    <div class="form-group input-group has-feedback">
                        <label> User role
                            <select id="" name="userRole" required>
                                <option value="${UserRole.BUYER}" selected>Buyer</option>
                                <option value="${UserRole.MANAGER}">Manager</option>
                            </select>
                        </label>
                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                    </div>


                    <div class="form-group input-group has-feedback">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-envelope"></span>
                        </span>
                        <form:input path="email" type="email" name="email"
                                    placeholder="Email Address" class="form-control"
                                    data-error="This email address is invalid" required="true" />
                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                    </div>

                    <div class="form-group input-group has-feedback">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-user"></span>
                        </span>
                        <form:input path="password" name="password" placeholder="Password" class="form-control" required="true" />
                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                    </div>

                    <%--<div class="form-group input-group has-feedback">--%>
                        <%--<label> Add Avatar--%>
                            <%--<input type="file" name="file" required />--%>
                        <%--</label>--%>
                        <%--<span class="glyphicon form-control-feedback" aria-hidden="true"></span>--%>
                    <%--</div>--%>



                    <button type="submit" class="btn btn-primary block full-width m-b">Register</button>

                </form:form>

            </div>
        </div>
        </c:if>
    </jsp:body>
</t:layout>


