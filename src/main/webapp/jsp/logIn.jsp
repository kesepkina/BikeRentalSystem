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
</head>
<body>
    <%@ include file="tiles/header.jsp"%>
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        <fmt:message key="logIn.login" /><br/>
        <input type="text" name="login" value=""/>
        <br/><fmt:message key="logIn.password" /><br/>
        <input type="password" name="password" value=""/>
        <br/><br/>
        ${errorUserMessage}<br/>
        <input type="submit" value="<fmt:message key="logIn.log_in" />">
        ${nullPage}
    </form>
    <form name="loginForm" method="post" action="controller">
        <input type="hidden" name="command" value="to_signup" />
        <input type="submit" value="<fmt:message key="logIn.sign_up" />">
        <br/>
        ${nullPage}
    </form>
</body>
</html>
