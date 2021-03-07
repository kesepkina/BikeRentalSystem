<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 03.02.2021
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
    <title>Error 500</title>
<body>
    <h2>ERROR 500</h2>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name: ${pageContext.errorData.servletName}
    <br/>
    Exception: ${pageContext.exception}
    <br/>
    Message from exception: ${pageContext.exception.message}
</body>
</html>
