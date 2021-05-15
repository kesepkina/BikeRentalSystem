<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 5/15/2021
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
</head>
<body>
<nav>
    <a href="<c:url value="/controller?command=to_admin_main"/>" class="logo">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" height="40px" >
    </a>
    <a href="<c:url value="/controller?command=to_orders"/>" class="home"><fmt:message key="header.orders" /></a>
    <a href="<c:url value="/controller?command=to_bicycles&bike-type=all"/>" class="home"><fmt:message key="header.bicycles" /></a>
    <a href="<c:url value="/controller?command=to_users"/>" class="home"><fmt:message key="header.users" /></a>
    <a style="width: max-content" href="<c:url value="/controller?command=logout"/>" class="home"><fmt:message key="header.log_out" /></a>
    <div class="language-menu">
        <div class="selected-lang"><fmt:message key="header.language"/></div>
        <ul>
            <c:set var="current_page" value="${pageContext.request.requestURI}" scope="session" />
            <c:choose>
                <c:when test="${sessionScope.locale == 'en_US'}" >
                    <li>
                        <a href="<c:url value="/controller?command=change_locale&locale=ru_RU"/>" class="ru">RU</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="<c:url value="/controller?command=change_locale&locale=en_US"/>" class="en">EN</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</body>
</html>
