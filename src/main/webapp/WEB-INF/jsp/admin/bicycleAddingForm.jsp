<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 5/17/2021
  Time: 11:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="bicycleAddingForm.title" /></title>
    <style>
        .asButton {
            background-color: antiquewhite;
            color: #655142;
            border-radius: 10px;
            border-color: #655142;
            height: 30px;
            cursor: pointer;
            padding-inline: 15px;
        }
        tr {
            height: 50px;
        }
        td {
            padding: 5px;
            border-bottom: 1px solid #ddd;
        }
        table {
            margin: 20px;
            width: 300px;
        }
        .bicycleForm {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 20px;
        }
    </style>
</head>
<body>
<%@ include file="../tiles/adminHeader.jsp"%>
<c:if test='${success.equals("yes!")}'>
    <script type="text/javascript">
        alert("Bicycle was added successfully.");
    </script>
</c:if>
<c:if test='${success.equals("no")}'>
    <script type="text/javascript">
        alert("Something went wrong, bicycle wasn't added.");
    </script>
</c:if>
    <form class="bicycleForm" name="bicycleForm" method="POST" action="controller">
        <input type="hidden" name="command" value="add_bicycle" />
        <table>
            <tr>
                <td>
                    <fmt:message key="bicycleAddingForm.brand" />
                </td>
                <td>
                    <input type="text" name="brand" value="" required pattern="[\p{Alpha}\s-]{0,30}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleAddingForm.model" />
                </td>
                <td>
                    <input type="text" name="model" value="" required pattern="[\p{Alpha}\s-]{0,30}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleAddingForm.type" />
                </td>
                <td>
                    <select id="bike-type" name="type">
                        <option value="all"><fmt:message key="main.bike-types.all"/></option>
                        <option value="MOUNTAIN_BIKE"><fmt:message key="main.bike-types.mtb"/></option>
                        <option value="CITY_BIKE"><fmt:message key="main.bike-types.city"/></option>
                        <option value="EBIKE"><fmt:message key="main.bike-types.ebike"/></option>
                        <option value="CHILDREN_BIKE"><fmt:message key="main.bike-types.children"/></option>
                        <option value="RACING_BIKE"><fmt:message key="main.bike-types.racing"/></option>
                        <option value="BMX"><fmt:message key="main.bike-types.bmx"/></option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleInfo.price_per_hour" />
                </td>
                <td>
                    <input type="number" name="price_per_hour" value="" required />
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleInfo.price_per_day" />
                </td>
                <td>
                    <input type="number" name="price_per_day" value="" required />
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleInfo.price_per_week" />
                </td>
                <td>
                    <input type="number" name="price_per_week" value="" required />
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleInfo.deposit" />
                </td>
                <td>
                    <input type="number" name="deposit" value="" required />
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycles.release_year" />
                </td>
                <td>
                    <input type="number" name="release_year" value="" min="1600" max="2021" required />
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycles.purchase_year" />
                </td>
                <td>
                    <input type="number" name="purchase_year" value="" min="1600" max="2021" required />
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message key="bicycleInfo.description" />
                </td>
                <td>
                    <input type="text" name="description" value="" required />
                </td>
            </tr>
        </table>
        <input class="asButton" type="submit" value="<fmt:message key="bicycleAddingForm.add" />">
    </form>
<%@ include file="../tiles/footer.jsp"%>
</body>
</html>
