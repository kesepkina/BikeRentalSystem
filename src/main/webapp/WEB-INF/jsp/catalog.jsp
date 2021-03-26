<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 05.03.2021
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="catalog.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/catalog_style.css">
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<div class="flex-container">
    <jsp:useBean id="bicyclesList" scope="request" type="java.util.List"/>
    <c:forEach items="${bicyclesList}" var="bicycle">
        <a href="catalog.jsp" class="cell"><div class="img_place"><img class="bicycle_photo" src="${pageContext.request.contextPath}/images/uploaded/bicycles/${bicycle.imagePath}" alt="bicycle_photo"></div>
            <br><div class="brand">${bicycle.brand}</div>
            <br><div class="model">${bicycle.model}</div>
            <br><div class="type">${bicycle.type}</div>
        </a>
    </c:forEach>
</div>
<%@ include file="tiles/footer.jsp"%>
</body>
</html>
