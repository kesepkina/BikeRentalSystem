<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 02.02.2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header_style.css">
</head>
<body>
    <nav>
        <div class="logo">
        </div>
        <div class="language-menu">
            <div class="selected-lang">
                Русский
            </div>
            <ul>
                <li>
                    <a href="<c:url value="/controller?command=change_locale&locale=en_US&currentPage="/>${pageContext.request.requestURI}" class="en">English</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=change_locale&locale=&currentPage="/>${pageContext.request.requestURI}" class="ru">Русский</a>
                </li>
            </ul>
        </div>
    </nav>
</body>
</html>
