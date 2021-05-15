<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 5/15/2021
  Time: 4:34 PM
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
    <title><fmt:message key="users.title"/></title>
    <style>
        th, td {
            border-bottom: 1px solid #ddd;
        }
        .trdata:hover {background-color: #f5f5f5;}
        tr {
            height: 60px;
        }
        th {
            background-color: #ded8ca4f;
        }
        table {
            margin: 20px;
            width: 96%;
        }
    </style>
</head>
<body>
<%@ include file="../tiles/adminHeader.jsp"%>
<table>
    <tr>
        <th>ID</th>
        <th><fmt:message key="users.login"/></th>
        <th><fmt:message key="users.name"/></th>
        <th><fmt:message key="users.surname"/></th>
        <th><fmt:message key="users.email"/></th>
        <th><fmt:message key="users.role"/></th>
        <th><fmt:message key="users.registered_at"/></th>
        <th><fmt:message key="users.status"/></th>
        <th></th>
    </tr>
    <jsp:useBean id="users" scope="session" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr class="trdata">
            <td>
                <c:out value="${user.userId}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <c:out value="${user.email}" />
            </td>
            <td>
                <c:out value="${user.role}" />
            </td>
            <td>
                <c:out value="${user.dateOfRegistration.getDayOfMonth()} ${user.dateOfRegistration.getMonth()} ${user.dateOfRegistration.getYear()}"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.isBlocked()==true}">
                        <fmt:message key="users.is_blocked"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="users.unblocked"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <button value="/controller?command=block_user&id=${user.userId}"><fmt:message key="users.block"/></button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>
