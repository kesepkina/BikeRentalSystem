<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 5/15/2021
  Time: 4:35 PM
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
    <title><fmt:message key="orders.title"/></title>
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
    <script>
        var pickUpTime=document.getElementById("pickUpTime").innerHTML;
        var pickUpTime2=pickUpTime.replace("T", " ");
        document.getElementById("pickUpTime").innerHTML = pickUpTime2
    </script>
</head>
<body>
<%@ include file="../tiles/adminHeader.jsp"%>
<table>
    <tr>
        <th>ID</th>
        <th><fmt:message key="orders.bicycle_id"/></th>
        <th><fmt:message key="orders.reserved_at"/></th>
        <th><fmt:message key="orders.pick_up_time"/></th>
        <th><fmt:message key="orders.return_time"/></th>
        <th><fmt:message key="orders.price"/></th>
        <th><fmt:message key="orders.status"/></th>
        <th><fmt:message key="orders.user_email"/></th>
        <th></th>
    </tr>
    <jsp:useBean id="orders" scope="session" type="java.util.List"/>
    <c:forEach items="${orders}" var="order">
        <tr class="trdata">
            <td>
                <c:out value="${order.reservationId}" />
            </td>
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
                <c:out value="${order.status}" />
            </td>
            <td>
                <c:out value="${order.userEmail}" />
            </td>
            <td>
                <button value="/controller?command=delete_order&id=${order.reservationId}"><fmt:message key="orders.delete"/></button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>