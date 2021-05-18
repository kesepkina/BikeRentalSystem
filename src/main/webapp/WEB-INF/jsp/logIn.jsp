<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 23.12.2020
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="logIn.title" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1 height=device-height">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        form {
            padding: 10px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
            margin-bottom: 20.4px;
            padding-right: 54%;
        }

        .asButton {
            background-color: antiquewhite;
            color: #655142;
            border-radius: 10px;
            border-color: #655142;
            height: 30px;
            cursor: pointer;
            padding-inline: 15px;
        }
        .main-content {
            background-image: url("${pageContext.request.contextPath}/images/login_background.png");
            padding-block: 112px;
        }
        .text {
            font-size: large;
        }
        .a1 {
            padding-top: 5px;
            padding-bottom: 10px;
            color: #655142;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<c:if test="${not empty userIsBlocked}">
    <script type="text/javascript">
        alert("Your account was blocked, you can't use our service.");
    </script>
</c:if>
    <div class="main-content">
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login" />
            <div class="text"><fmt:message key="logIn.login" /></div>
            <input type="text" name="login" value="" required/>
            <br/><div class="text"><fmt:message key="logIn.password" /></div>
            <input type="password" name="password" value="" required/>
            <br/>
            ${errorUserMessage}
            <input class="asButton" type="submit" value="<fmt:message key="logIn.log_in" />">
            <a class="a1" href="<c:url value="/controller?command=to_signup"/>"><fmt:message key="logIn.sign_up"/></a>
        </form>
    </div>
<%@ include file="tiles/footer.jsp"%>
</body>
</html>
