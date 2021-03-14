<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 24.12.2020
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />


<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="main.title" /></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        h1 {
           letter-spacing: normal;
            font-size: 60px;
            color: antiquewhite;
            font-family: 'Noto Sans', sans-serif;
            text-shadow: 3px 2px 3px #31251c;
            width: max-content;
        }
    </style>
</head>
<body>
    <%@ include file="tiles/header.jsp"%>
    <div class="w3-display-container w3-content" style="max-width:100%;" id="home">
        <img src="${pageContext.request.contextPath}/images/home_background.jpg" alt="Background" width=100% >
        <div class="w3-display-topmiddle w3-margin-top w3-center">
            <h1><fmt:message key="main.welcome"/></h1>
            <br>
            <form name="infoForm" method="POST" action="controller">
                <input type="hidden" name="command" value="display_bicycles"/>
                <input class="w3-button w3-yellow w3-round-large" type="submit" value="<fmt:message key="main.find"/>">
            </form>
        </div>
    </div>
    <form name="logoutForm" method="POST" action="controller">
        <input type="hidden" name="command" value="logout" />
        <input type="submit" value="log out">
    </form>
</body>
</html>
