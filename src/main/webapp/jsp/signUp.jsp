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
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="signUp.title" /></title>
</head>
<body>
<jsp:include page="tiles/header.jsp"/>
<form name="signupForm" method="POST" action="controller">
    <input type="hidden" name="command" value="signup" />
    <fmt:message key="signUp.surname" /><br/>
    <input type="text" name="surname" value="" required/>
    <br/><fmt:message key="signUp.name" /><br/>
    <input type="text" name="name" value="" required/>
    <br/><fmt:message key="signUp.email" /><br/>
    <input type="email" name="email" value="" required pattern="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"/>
    ${errorEmailMessage}
    <br/><fmt:message key="signUp.username" /><br/>
    <input type="text" name="username" value="" required pattern="[a-zA-Z0-9._]{5,20}"
           title='must include only letters, ciphers, characters ".", "_" and have from 5 to 20 characters' />
    ${errorUsernameMessage}
    <br/><fmt:message key="signUp.password" /><br/>
    <input type="password" name="password" value="" required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\S+$).{8,20}$"
           title='must include at least one letter in upper and in lower case, at least one cipher, at least one special character ("@", "#". "$", "%", "^", "&", "(" or ")", no spaces and have from 8 to 20 characters'/>
    ${errorPasswordMessage}
    <br/><br/>
    <input type="submit" value="<fmt:message key="signUp.sign_up" />">
    <br/>${errorUserMessage}
</form>
<form name="infoForm" method="POST" action="controller">
    <input type="hidden" name="command" value="go_to_login"/>
    <input type="submit" value="<fmt:message key="signUp.log_in" />">
</form>
</body>
</html>