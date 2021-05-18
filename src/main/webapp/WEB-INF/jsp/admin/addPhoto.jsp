<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 5/18/2021
  Time: 2:37 AM
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
    <title>Add an image</title>
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
<fmt:message key="addPhoto.message"/>
<form class="bicycleForm" method="post" action="fileuploadservlet" enctype="multipart/form-data">
<input type="hidden" name="folder" value="bicycles">
<table>
<tr>
    <td>
        <fmt:message key="bicycleAddingForm.image" />
    </td>
    <td>
        <input type="file" name="file" />
    </td>
</tr>
</table>
<input class="asButton" type="submit" value="<fmt:message key="bicycleAddingForm.add" />">
</form>
</body>
</html>
