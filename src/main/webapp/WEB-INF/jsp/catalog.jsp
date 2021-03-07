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
<!DOCTYPE html>
<html>
<head>
    <title>Catalog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .flex-container {
            padding: 40px;
            display: flex;
            flex-wrap: wrap;
            background-color: black;
        }

        .cell {
            padding: 20px;
            background-color: #ffffff;
            width: 300px;
            margin: 10px;
            text-align: center;
            border-radius: 10px;
            line-height: 20px;
        }

        .cell:hover {
            box-shadow: 0px 0px 5px 3px rgb(140, 96, 73);
        }

        .img_place {
            height: 50%;
            align-content: center;
        }

        img {
            border-radius: 25px;
            max-height: 100%;
            max-width: 90%;
        }

        .brand {
            font-size: 30px;
            color: #4f3f24;
        }

        .model {
            font-size: 20px;
            color: #4f3f24;
        }

        .type{
            font-size: 25px;
            color: #564528b8;
        }
    </style>
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<div class="flex-container">
    <jsp:useBean id="bicyclesList" scope="request" type="java.util.List"/>
    <c:forEach items="${bicyclesList}" var="bicycle">
        <a href="catalog.jsp" class="cell"><div class="img_place"><img src="${pageContext.request.contextPath}/images/uploaded/${bicycle.imagePath}" alt="bicycle_photo"></div>
            <br><div class="brand">${bicycle.brand}</div>
            <br><div class="model">${bicycle.model}</div>
            <br><div class="type">${bicycle.type}</div>
        </a>
    </c:forEach>
</div>
</body>
</html>
