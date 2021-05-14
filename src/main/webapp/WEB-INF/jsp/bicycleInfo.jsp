<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 26.03.2021
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.text" />

<html>
<head>
    <title>Bicycle</title>
    <style>
        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #655142;
            color: antiquewhite;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #65514275;
            width: 260px; /* Could be more or less, depending on screen size */
            border-radius: 42px;
        }

        /* The Close Button */
        .close {
            color: #faebd770;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: antiquewhite;
            text-decoration: none;
            cursor: pointer;
        }

        button, .rentInput {
            background-color: antiquewhite;
            color: #655142;
            border-radius: 10px;
            border-color: #655142;
            height: 30px;
            cursor: pointer;
            padding-inline: 15px;
        }

        .rentInput {
            width: max-content;
        }

        form {
            margin-bottom: 0px;
        }

        .formButton {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<c:choose>
    <c:when test="${not empty sessionScope.chosenBicycle.imagePath}" >
    <img alt="Bicycle photo" src="${pageContext.request.contextPath}/image/bicycles/${sessionScope.chosenBicycle.imagePath}" width="200px">
    </c:when>
    <c:otherwise>
    <img alt="Default profile photo" src="${pageContext.request.contextPath}/images/bicycle.png">
    </c:otherwise>
</c:choose>
<br>
<table>
    <tr>
        <td><fmt:message key="bicycleInfo.brand"/></td>
        <td>${sessionScope.chosenBicycle.brand}</td>
    </tr>
    <tr>
        <td><fmt:message key="bicycleInfo.model"/></td>
        <td>${sessionScope.chosenBicycle.model}</td>
    </tr>
    <tr>
        <td><fmt:message key="bicycleInfo.type"/></td>
        <td>${sessionScope.chosenBicycle.type}</td>
    </tr>
    <tr>
        <td><fmt:message key="bicycleInfo.description"/></td>
        <td><c:choose><c:when test="${not empty sessionScope.chosenBicycle.description}">${sessionScope.chosenBicycle.description}</c:when>
        <c:otherwise> - </c:otherwise></c:choose></td>
    </tr>
</table>
<button id="openRentForm"><fmt:message key="bicycleInfo.rent"/></button>

<div id="rentForm" class="modal">

    <div class="modal-content">
        <span class="close">&times;</span>
        <br/>
        <form name="rentForm" method="POST" action="controller">
            <input type="hidden" name="command" value="rent" />
            <fmt:message key="bicycleInfo.rentform.from" /><br/>
            <input type="datetime-local" name="from" value="" required/>
            <br/>
            <br/><fmt:message key="bicycleInfo.rentform.to" /><br/>
            <input type="datetime-local" name="to" value="" required/>
            <br/><br/>
            <div class="formButton">
            <input class="rentInput" type="submit" value="<fmt:message key="bicycleInfo.rent" />">
            </div>
        </form>
    </div>

</div>
<script>
    var modal = document.getElementById("rentForm");

    var btn = document.getElementById("openRentForm");

    var span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        modal.style.display = "block";
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
<%@ include file="tiles/footer.jsp"%>
</body>
</html>
