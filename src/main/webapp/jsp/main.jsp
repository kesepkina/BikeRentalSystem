<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 24.12.2020
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Main page</title>
</head>
<body>
    <form name="logoutForm" method="POST" action="controller">
        <input type="hidden" name="command" value="logout" />
        <h3>Welcome</h3>
        <hr/>
        Hello, ${user}!
        <hr/>
        <input type="submit" value="log out">
    </form>
    <form name="infoForm" method="POST" action="controller">
        <input type="hidden" name="command" value="show_Info"/>
        <br/>
        <input type="submit" value="info about project">
    </form>
</body>
</html>
