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
        td {
            padding: 5px;
        }
        table {
            margin: 20px;
            width: 96%;
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
    <script>
        var pickUpTime=document.getElementById("pickUpTime").innerHTML;
        var pickUpTime2=pickUpTime.replace("T", " ");
        document.getElementById("pickUpTime").innerHTML = pickUpTime2
    </script>
</head>
<body>
<%@ include file="../tiles/adminHeader.jsp"%>
<c:if test='${success.equals("yes!")}'>
<script type="text/javascript">
    alert("Order was deleted successfully.");
</script>
</c:if>
<c:if test='${success.equals("no")}'>
<script type="text/javascript">
    alert("Something went wrong, order wasn't deleted.");
</script>
</c:if>
<c:if test='${successUpd.equals("yes!")}'>
    <script type="text/javascript">
        alert("Order status was updated successfully.");
    </script>
</c:if>
<c:if test='${successUpd.equals("no")}'>
    <script type="text/javascript">
        alert("Something went wrong, order status wasn't updated.");
    </script>
</c:if>
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
        <th><fmt:message key="orders.bicycle_id"/></th>
        <th><fmt:message key="orders.reserved_at"/></th>
        <th><fmt:message key="orders.pick_up_time"/></th>
        <th><fmt:message key="orders.return_time"/></th>
        <th><fmt:message key="orders.price"/></th>
        <th><fmt:message key="orders.user_email"/></th>
        <th><fmt:message key="orders.status"/></th>
        <th></th>
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
                <c:out value="${order.userEmail}" />
            </td>
            <td>
                <c:out value="${order.status.getValue()}" />
            </td>
            <td>
                <form name="changeStatus" method="POST" action="controller">
                        <select id="order-status" name="order-status">
                            <option value="PENDING"><fmt:message key="orders.status.pending"/></option>
                            <option value="CONFIRMED"><fmt:message key="orders.status.confirmed"/></option>
                            <option value="CANCELLED_BY_LANDLORD"><fmt:message key="orders.status.cancelled"/></option>
                            <option value="REFUSED"><fmt:message key="orders.status.refused"/></option>
                        </select>
                    <input type="hidden" name="command" value="change_order_status" />
                    <input type="hidden" name="orderId" value="${order.reservationId}" />
                    <input type="submit" value="<fmt:message key="orders.change_status"/>">
                </form>
            </td>
            <td>
                <form name="deleteOrder" method="POST" action="controller">
                    <input type="hidden" name="command" value="delete_order" />
                    <input type="hidden" name="orderId" value="${order.reservationId}" />
                    <input type="submit" value="<fmt:message key="orders.delete"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form style="display: flex; justify-content: center; margin: 20px;" name="downloadTable" method="POST" action="controller">
    <input type="hidden" name="command" value="download_orders" />
    <input class="asButton" type="submit" value="<fmt:message key="orders.download"/>">
</form>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>