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
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />

<html>
<head>
    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<c:if test="${not empty updatingError}">
    <script type="text/javascript">
            alert("Oops! Something went wrong, your photo wasn't uploaded.");
    </script>
</c:if>
<c:choose>
    <c:when test="${not empty sessionScope.user.photoName}" >
        <img alt="Profile photo" src="${pageContext.request.contextPath}/image/profiles/${sessionScope.user.photoName}" width="200px">
        <form method="post" action="fileuploadservlet" enctype="multipart/form-data">
            <input type="hidden" name="folder" value="profiles">
            <input type="file" name="file" />
            <input type="submit" value="Change" />
        </form>
    </c:when>
    <c:otherwise>
        <img alt="Default profile photo" src="${pageContext.request.contextPath}/images/avatar.png">
        <form method="post" action="fileuploadservlet" enctype="multipart/form-data">
            <input type="hidden" name="folder" value="profiles">
            <input type="file" name="file" />
            <input type="submit" value="Upload" />
        </form>
    </c:otherwise>
</c:choose>
<br>
<fmt:message key="profile.name"/>: ${sessionScope.user.name}
<br>
<fmt:message key="profile.surname"/>: ${sessionScope.user.surname}
<br>
<fmt:message key="profile.email"/>: ${sessionScope.user.email}
<%@ include file="tiles/footer.jsp"%>
</body>
</html>