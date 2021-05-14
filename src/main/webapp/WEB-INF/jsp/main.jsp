<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 24.12.2020
  Time: 0:22
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
    <title><fmt:message key="main.title" /></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1 height=device-height">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        h1 {
           letter-spacing: normal;
            font-size: 60px;
            color: antiquewhite;
            font-family: 'Noto Sans', sans-serif;
            text-shadow: 3px 2px 3px #31251c;
            width: max-content;
        }
         select {
             width: max-content;
             padding: 16px 20px;
             border: none;
             background-color: #655142;
             color: antiquewhite;
             margin: 5px;
             border-radius: 10px;
         }
        .form-inline {
            display: flex;
            flex-flow: row wrap;
            align-items: center;
            flex-wrap: nowrap;
            align-content: space-between;
            width: fit-content;
            margin: auto;
            background-color: #65514275;
            padding: 5px 10px;
            border-radius: 10px;
        }
        .find-button {
            background-color: antiquewhite;
            color: #655142;
            border-radius: 10px;
            margin: 0px 10px;
            height: 40px;
            border-color: #655142;
        }

        .find-button:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>
    <%@ include file="tiles/header.jsp"%>
    <div class="w3-display-container w3-content" style="max-width:100%;" id="home">
        <img src="${pageContext.request.contextPath}/images/home_background.jpg" alt="Background" width=100% >
        <div class="w3-display-topmiddle w3-margin-top w3-center">
            <h1><fmt:message key="main.welcome"/></h1>
            <br>
            <form class="form-inline" name="infoForm" method="POST" action="controller">
                <input type="hidden" name="command" value="display_bicycles"/>
                <div>
                    <select id="bike-type" name="bike-type">
                        <option value="all"><fmt:message key="main.bike-types.all"/></option>
                        <option value="MOUNTAIN_BIKE"><fmt:message key="main.bike-types.mtb"/></option>
                        <option value="CITY_BIKE"><fmt:message key="main.bike-types.city"/></option>
                        <option value="EBIKE"><fmt:message key="main.bike-types.ebike"/></option>
                        <option value="CHILDREN_BIKE"><fmt:message key="main.bike-types.children"/></option>
                        <option value="RACING_BIKE"><fmt:message key="main.bike-types.racing"/></option>
                        <option value="BMX"><fmt:message key="main.bike-types.bmx"/></option>
                    </select>
                </div>
                <input class="find-button" type="submit" value="<fmt:message key="main.find"/>">
            </form>
        </div>
    </div>
    <%@ include file="tiles/footer.jsp"%>
</body>
</html>
