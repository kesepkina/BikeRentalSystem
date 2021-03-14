<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <c:set var="user_role" value="GUEST" scope="session" />
        <jsp:forward page="/WEB-INF/jsp/main.jsp" />
    </body>
</html>