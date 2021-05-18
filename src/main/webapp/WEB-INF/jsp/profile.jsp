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
        td {
            padding: 5px;
        }
        table {
            margin: 20px;
            width: 96%;
        }
        .main-content {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 20px;
            font-size: larger;
        }
    </style>
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<c:if test="${not empty updatingError}">
    <script type="text/javascript">
            alert("Oops! Something went wrong, your photo wasn't uploaded.");
    </script>
</c:if>
<div class="main-content">
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
</div>
<br/>
<p style="text-align: center; font-size: x-large"><fmt:message key="profile.orders"/></p>
<table>
    <tr>
        <th><fmt:message key="orders.bicycle_id"/></th>
        <th><fmt:message key="orders.reserved_at"/></th>
        <th><fmt:message key="orders.pick_up_time"/></th>
        <th><fmt:message key="orders.return_time"/></th>
        <th><fmt:message key="orders.price"/></th>
        <th><fmt:message key="orders.status"/></th>
        <th></th>
    </tr>
    <jsp:useBean id="orders" scope="session" type="java.util.List"/>
    <c:forEach items="${orders}" var="order">
        <tr class="trdata">
            <td>
                <c:out value="${order.bicycleId}" />
            </td>
            <td>
                <c:out value="${order.reservedAt.getDayOfMonth()} ${order.reservedAt.getMonth()} ${order.reservedAt.getYear()} ${order.reservedAt.getHour()}:${order.reservedAt.getMinute()}" />
            </td>
            <td>
                <c:out value="${order.pickUpTime.getDayOfMonth()} ${order.pickUpTime.getMonth()} ${order.pickUpTime.getYear()} ${order.pickUpTime.getHour()}:${order.pickUpTime.getMinute()}" />
            </td>
            <td>
                <c:out value="${order.returnTime.getDayOfMonth()} ${order.returnTime.getMonth()} ${order.returnTime.getYear()} ${order.returnTime.getHour()}:${order.returnTime.getMinute()}" />
            </td>
            <td>
                <c:out value="${order.countedPrice}" /> BYN
            </td>
            <td>
                <c:out value="${order.status.getValue()}" />
            </td>
            <td>
                <form name="deleteOrder" method="POST" action="controller">
                    <input type="hidden" name="command" value="delete_order" />
                    <input type="hidden" name="orderId" value="${order.reservationId}" />
                    <input type="submit" value="<fmt:message key="profile.cancel"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="tiles/footer.jsp"%>
</body>
</html>