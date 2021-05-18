<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 5/16/2021
  Time: 1:47 PM
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
    <style>
        .required-field::after {
            content: "*";
            color: #9b3030;
            margin-left:2px
        }
        form {
            padding: 10px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
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
<%@ include file="../tiles/adminHeader.jsp"%>
<div class="main-content">
    <form name="signupForm" method="POST" action="controller">
        <input type="hidden" name="command" value="signup" />
        <input type="hidden" name="userRole" value="ADMIN" />
        <br/><span class="required-field"><fmt:message key="signUp.name" /></span>
        <input type="text" name="name" value="" required pattern="[\p{Alpha}\s-]{0,30}"/>
        <br/><span class="required-field"><fmt:message key="signUp.surname" /></span>
        <input type="text" name="surname" value="" required pattern="[\p{Alpha}\s-]{0,30}"/>
        <br/><span class="required-field"><fmt:message key="signUp.email" /></span>
        <input type="email" name="email" value="" required pattern="^[a-zA-Z0-9_+&*-]+(?:.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,7}$"/>
        ${errorEmailMessage}
        <br/><span class="required-field"><fmt:message key="signUp.login" /></span>
        <input type="text" name="login" value="" required pattern="[a-zA-Z0-9._]{5,20}"
               title='must include only letters, ciphers, characters ".", "_" and have from 5 to 20 characters' />
        ${errorLoginMessage}
        <br/><span class="required-field"><fmt:message key="signUp.password" /></span>
        <input type="password" name="password" value="" required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\S+$).{8,20}$"
               title='must include at least one letter in upper and in lower case, at least one cipher, at least one special character ("@", "#". "$", "%", "^", "&", "(" or ")", no spaces and have from 8 to 20 characters'/>
        ${errorPasswordMessage}
        <br/><span class="required-field"><fmt:message key="signUp.passwordConfirming" /></span>
        <input type="password" name="passwordConfirming" value="" required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\S+$).{8,20}$"/>
        ${errorPasswordConfirmingMessage}
        <br/><br/>
        <input class="asButton" type="submit" value="<fmt:message key="signUp.sign_up" />">
        ${errorUserMessage}
        <a class="a1" href="<c:url value="/controller?command=to_login" />"><fmt:message key="signUp.log_in"/></a>
    </form>
</div>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>
