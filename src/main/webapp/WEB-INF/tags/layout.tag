<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@attribute name="header" fragment="true" required="false" %>
<%@attribute name="footer" fragment="true" required="false" %>
<%@attribute name="title" fragment="true" required="false" %>
<%--<%@attribute name="script-block" fragment="true" required="false" %>--%>



<c:url var = "goToIndex" value="/" scope="request"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><jsp:invoke fragment="header"/></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/site.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/signin.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/startup.css"/>" rel="stylesheet">
</head>

<body>
    <div class="wrap">
        <div id="pageheader">
            <nav id="mainLogo" class="navbar-inverse navbar-fixed-top navbar">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainLogo-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <span class="navbar-brand"><a href="${goToIndex}">CHAT SYSTEM</a></span>
                    </div>
                    <div id="mainLogo-collapse" class="collapse navbar-collapse">
                        <ul class="navbar-nav navbar-right nav">

                            <sec:authorize access="isAuthenticated()">
                                <sec:authentication property="principal.username" var="userName"/>
                                <li><a href="<c:url value="/logout"/>">Log Out: ${userName}</a></li>
                            </sec:authorize>
                        </ul>
                    </div>
                </div>
            </nav>
            <h2><b><jsp:invoke fragment="header"/></b></h2>
        </div>
        <div id="body" class="container">
            <div id="content">
                <jsp:doBody/>
            </div>
        </div>
    </div>

    <footer class="footer"></footer>

    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/js/site.js"/>"></script>

</body>
</html>
