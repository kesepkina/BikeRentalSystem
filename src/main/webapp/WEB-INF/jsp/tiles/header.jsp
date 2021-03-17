<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 02.02.2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <a href="<c:url value="/controller?command=to_main"/>" class="logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" height="40px" >
        </a>
        <div class="profile-menu">
            <div class="profile"><fmt:message key="header.profile"/></div>
            <ul>
                <c:choose>
                    <c:when test="${sessionScope.user_role == 'GUEST'}" >
                        <li>
                            <a href="<c:url value="/controller?command=to_login" />"><fmt:message key="header.log_in"/></a>
                        </li>
                        <li>
                            <a href="<c:url value="/controller?command=to_signup"/>"><fmt:message key="header.sign_up"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="<c:url value="/controller?command=to_profile"/>" ><fmt:message key="header.profile"/></a>
                        </li>
                        <li>
                            <a href="<c:url value="/controller?command=logout"/>" ><fmt:message key="header.log_out"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <div class="language-menu">
            <div class="selected-lang"><fmt:message key="header.language"/></div>
            <ul>
                <c:set var="currentPage" value="${pageContext.request.requestURI}" scope="session" />
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
