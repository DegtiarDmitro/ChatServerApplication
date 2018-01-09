<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<t:layout>
    <jsp:attribute name="header">
        CHAT-SYSTEM
    </jsp:attribute>
    <jsp:body>
        <div id="chatbox-wrapper">
            <div id="chatbox">
                <table id="messages">
                    <%--<c:forEach var="message" items="${messages}">--%>
                        <%--<tr>--%>
                            <%--&lt;%&ndash;<td hidden>${message.id}</td>&ndash;%&gt;--%>
                            <%--<td><b>${message.user}:</b></td>--%>
                            <%--<td>${message.text}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                </table>
            </div>
            <form name="message" action="" method="post" id="message">
                <input type="hidden" id="username" name="username" value="${user.getUsername()}">
                <input type="hidden" id="pass" name="pass" value="${user.getPassword()}">
                <input name="usermsg" id="usermsg" value="" size="63"/>
                <input name="submitmsg" type="submit" id="submitmsg" value="Send"/>
                <input type="file" id="filename" />
            </form>
        </div>

    </jsp:body>
</t:layout>
<script src="<c:url value="/js/chat.js"/>"></script>

<%--<script language="JavaScript" type="text/JavaScript" src="/js/chat.js"></script>--%>