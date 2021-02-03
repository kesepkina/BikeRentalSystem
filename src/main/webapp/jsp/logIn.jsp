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
<fmt:setBundle basename="property.log_in" var="base"/>

<fmt:message bundle="${base}" key="sign_up" var="sign_up" />
<fmt:message bundle="${base}" key="log_in" var="log_in" />
<fmt:message bundle="${base}" key="title" var="title" />
<fmt:message bundle="${base}" key="username" var="username" />
<fmt:message bundle="${base}" key="password" var="pass" />
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="tiles/header.jsp"/>
    <form name="loginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
        ${username}<br/>
        <input type="text" name="username" value="" required pattern="[a-zA-Zа-яА-Я0-9._]{5,}"
               title='must include only letters, ciphers, characters ".", "_" and have at least 5 characters'/>
        ${errorUsernameMessage}
        <br/>${pass}<br/>
        <input type="password" name="password" value="" required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\S+$).{8,20}$"
               title='must include at least one letter in upper and in lower case, at least one cipher, at least one special character ("@", "#". "$", "%", "^", "&", "(" or ")", no spaces and have from 8 to 20 characters'/>
        ${errorPasswordMessage}
        <br/><br/>
        <input type="submit" value="${log_in}">
        <br/>${errorUserMessage}
        ${nullPage}
    </form>
    <form name="loginForm" method="post" action="controller">
        <input type="hidden" name="command" value="signup_page" />
        <input type="submit" value="${sign_up}">
        <br/>
        ${nullPage}
    </form>
</body>
</html>
