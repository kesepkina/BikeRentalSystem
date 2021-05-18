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
            border-radius: 20px;
            border-color: #655142;
            height: 40px;
            cursor: pointer;
            font-size: x-large;
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
        .bcImg {
            width: 400px;
            margin: 20px;
        }
        .main-content {
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            align-content: center;
            align-items: flex-start;
            justify-content: space-evenly;
        }
        .price-list {
            display: flex;
            align-items: center;
            flex-direction: column;
            margin: 20px;
        }
        .price-list-title {
            font-size: x-large;
            margin-top: 10px;
            font-weight: bold;
            margin-bottom: 0;
        }
        .a1 {
            color: #5b4636;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%@ include file="tiles/header.jsp"%>
<c:if test="${not empty infoModal}">
    <script type="text/javascript">
        alert("Your order was added successfully.");
    </script>
</c:if>
<div class="main-content">
<c:choose>
    <c:when test="${not empty sessionScope.chosen_bicycle.imagePath}" >
    <img class="bcImg" alt="Bicycle photo" src="${pageContext.request.contextPath}/image/bicycles/${sessionScope.chosen_bicycle.imagePath}">
    </c:when>
    <c:otherwise>
    <img class="bcImg" alt="Default profile photo" src="${pageContext.request.contextPath}/images/bicycle.png">
    </c:otherwise>
</c:choose>
<br>
<table style="width: 500px">
    <tr>
        <td style="color: #443427"><fmt:message key="bicycleInfo.brand"/></td>
        <td style="width: 70%">${sessionScope.chosen_bicycle.brand}</td>
    </tr>
    <tr>
        <td style="color: #443427"><fmt:message key="bicycleInfo.model"/></td>
        <td style="width: 70%">${sessionScope.chosen_bicycle.model}</td>
    </tr>
    <tr>
        <td style="color: #443427"><fmt:message key="bicycleInfo.type"/></td>
        <td style="width: 70%">${sessionScope.chosen_bicycle.type.getValue()}</td>
    </tr>
    <tr>
        <td style="color: #443427"><fmt:message key="bicycleInfo.description"/></td>
        <td style="width: 70%"><c:choose><c:when test="${not empty sessionScope.chosen_bicycle.description}">${sessionScope.chosenBicycle.description}</c:when>
        <c:otherwise> - </c:otherwise></c:choose></td>
    </tr>
</table>
    <div class="price-list">
        <p class="price-list-title" ><fmt:message key="bicycleInfo.price_list"/></p>
<table>
    <tr>
        <td><fmt:message key="bicycleInfo.deposit"/></td>
        <td>${sessionScope.price_list.deposit} BYN</td>
    </tr>
    <tr>
        <td><fmt:message key="bicycleInfo.price_per_hour"/></td>
        <td>${sessionScope.price_list.pricePerHour} BYN</td>
    </tr>
    <tr>
        <td><fmt:message key="bicycleInfo.price_per_day"/></td>
        <td>${sessionScope.price_list.pricePerDay} BYN</td>
    </tr>
    <tr>
        <td><fmt:message key="bicycleInfo.price_per_week"/></td>
        <td>${sessionScope.price_list.pricePerWeek} BYN</td>
    </tr>
</table>
    </div>
    <div class="message" style="width: 500px; text-align: center; color: #ad998b;">
<c:choose>
    <c:when test="${sessionScope.user.role == 'CLIENT'}" >
        <button id="openRentForm"><fmt:message key="bicycleInfo.rent"/></button>
    </c:when>
    <c:otherwise>
        <a class="a1" href="<c:url value="/controller?command=to_login" />"><fmt:message key="bicycleInfo.log_in"/></a>
        <fmt:message key="bicycleInfo.or"/>
        <a class="a1" href="<c:url value="/controller?command=to_signup"/>"><fmt:message key="bicycleInfo.sign_up"/></a>
        <fmt:message key="bicycleInfo.to_rent"/>
    </c:otherwise>
</c:choose>
    </div>

<div id="rentForm" class="modal">

    <div class="modal-content">
        <span class="close">&times;</span>
        <br/>
        <form name="rentForm" method="POST" action="controller" >
            <input type="hidden" name="command" value="rent" />
            <fmt:message key="bicycleInfo.rentform.from" /><br/>
            <input id="from" type="datetime-local" name="from" value="" min="2021-14-05T12:30" max="2021-31-12T23:59" step="1800" required/>
            <br/>
            <br/><fmt:message key="bicycleInfo.rentform.duration" /><br/>
            <input id="amount" type="number" name="amount" value="1" max="23" required/>
            <select id="time-format" name="time-format">
                <option value="hours" ><fmt:message key="bicycleInfo.rentform.hours"/></option>
                <option value="days" ><fmt:message key="bicycleInfo.rentform.days"/></option>
                <option value="weeks" ><fmt:message key="bicycleInfo.rentform.weeks"/></option>
            </select>
            <br/><br/>
            <div class="formButton">
                <input class="rentInput" type="submit" value="<fmt:message key="bicycleInfo.rent" />">
            </div>
        </form>
    </div>
</div>
</div>
<script>
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;
    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    }
    if(mm<10){
        mm='0'+mm
    }
    var HH = today.getHours();
    var MM = today.getMinutes();
    if(MM>=0 && MM<30){
        MM=30
    } else {
        MM='00'
        HH+=1
    }
    if(HH<10){
        HH='0'+HH
    }
    today = yyyy+'-'+mm+'-'+dd+'T'+HH+':'+MM;
    document.getElementById("from").setAttribute("min", today);
    document.getElementById("from").setAttribute("value", today);

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
