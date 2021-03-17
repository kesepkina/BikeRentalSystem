<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 17.03.2021
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.profile_photo}" >
        <img alt="Profile photo" src="${pageContext.request.contextPath}/images/uploaded/profiles/${sessionScope.profile_photo}">
    </c:when>
    <c:otherwise>
        <img alt="Default profile photo" src="../../images/uploaded/avatar.png">
    </c:otherwise>
</body>
</html>