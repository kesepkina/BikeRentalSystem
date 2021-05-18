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
    <title><fmt:message key="bicycles.title"/></title>
    <style>
        th, td {
            border-bottom: 1px solid #ddd;
        }
        .trdata:hover {background-color: #f5f5f5;}
        tr {
            height: 60px;
        }
        td {
            padding: 5px;
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
        <th><fmt:message key="bicycles.brand"/></th>
        <th><fmt:message key="bicycles.model"/></th>
        <th><fmt:message key="bicycles.release_year"/></th>
        <th><fmt:message key="bicycles.purchase_year"/></th>
        <th><fmt:message key="bicycles.description"/></th>
        <th><fmt:message key="bicycles.type"/></th>
        <th><fmt:message key="bicycles.price_list"/></th>
        <th><fmt:message key="bicycles.image"/></th>
        <th></th>
    </tr>
    <jsp:useBean id="bicyclesList" scope="session" type="java.util.List"/>
    <c:forEach items="${bicyclesList}" var="bicycle">
        <tr class="trdata">
            <td>
                <c:out value="${bicycle.bicycleId}" />
            </td>
            <td>
                <c:out value="${bicycle.brand}" />
            </td>
            <td>
                <c:out value="${bicycle.model}" />
            </td>
            <td>
                <c:choose>
                    <c:when test="${bicycle.releaseYear!=0}">
                        <c:out value="${bicycle.releaseYear}" />
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${bicycle.purchaseYear!=0}">
                        <c:out value="${bicycle.purchaseYear}" />
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:out value="${bicycle.description}" />
            </td>
            <td>
                <c:out value="${bicycle.type.getValue()}" />
            </td>
            <td>
                <c:out value="${bicycle.priceListId}" />
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty bicycle.imagePath}">
                        +
                    </c:when>
                </c:choose>
            </td>
            <td>
                <button value="/controller?command=delete_bicycle&id=${bicycle.bicycleId}"><fmt:message key="bicycles.delete"/></button>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>
