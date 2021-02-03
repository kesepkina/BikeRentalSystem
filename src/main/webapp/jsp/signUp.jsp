<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 27.12.2020
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="property.sign_up" var="base"/>

<fmt:message bundle="${base}" key="title" var="title" />
<fmt:message bundle="${base}" key="surname" var="surname" />
<fmt:message bundle="${base}" key="name" var="name" />
<fmt:message bundle="${base}" key="email" var="email" />
<fmt:message bundle="${base}" key="username" var="username" />
<fmt:message bundle="${base}" key="password" var="pass" />
<fmt:message bundle="${base}" key="log_in" var="login" />
<fmt:message bundle="${base}" key="sign_up" var="signup" />
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="tiles/header.jsp"/>
<form name="signupForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signup" />
    ${surname}<br/>
    <input type="text" name="surname" value="" required/>
    <br/>${name}<br/>
    <input type="text" name="name" value="" required/>
    <br/>${email}<br/>
    <input type="email" name="email" value="" required/>
    <br/>${username}<br/>
    <input type="text" name="username" value="" required pattern="[a-zA-Z0-9._]{5,20}"
           title='must include only letters, ciphers, characters ".", "_" and have from 5 to 20 characters' />
    ${errorUsernameMessage}
    <br/>${pass}<br/>
    <input type="password" name="password" value="" required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\S+$).{8,20}$"
           title='must include at least one letter in upper and in lower case, at least one cipher, at least one special character ("@", "#". "$", "%", "^", "&", "(" or ")", no spaces and have from 8 to 20 characters'/>
    ${errorPasswordMessage}
    <br/><br/>
    <input type="submit" value="${signup}">
    <br/>${errorUserMessage}
</form>
<form name="infoForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login_page"/>
    <input type="submit" value="${login}">
</form>
</body>
</html>
