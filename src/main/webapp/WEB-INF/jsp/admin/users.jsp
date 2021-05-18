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
        td {
            padding: 5px;
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
    </style>
</head>
<body>
<%@ include file="../tiles/adminHeader.jsp"%>
<c:if test='${successDownload.equals("yes!")}'>
    <script type="text/javascript">
        alert("File was downloaded successfully.");
    </script>
</c:if>
<c:if test='${successDownload.equals("no")}'>
    <script type="text/javascript">
        alert("Something went wrong, file wasn't downloaded.");
    </script>
</c:if>
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
                <c:if test='${user.role=="CLIENT"}'>
                    <button value="/controller?command=block_user&id=${user.userId}"><fmt:message key="users.block"/></button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<form style="display: flex; justify-content: center; margin: 20px;" name="downloadTable" method="POST" action="controller">
    <input type="hidden" name="command" value="download_users" />
    <input class="asButton" type="submit" value="<fmt:message key="orders.download"/>">
</form>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>
