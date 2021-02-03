<%--
  Created by IntelliJ IDEA.
  User: kesep
  Date: 02.02.2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<ul>
    <form class="hidden_locale_form" name = "ru_locale_form" action="controller" method="POST">
        <input type="hidden" name="command" value="change_locale" />
        <input type="hidden" name="currentPage" value="${pageContext.request.requestURI}" />
        <button type="submit" name="locale" value="" >Русский</button>
        <button type="submit" name="locale" value="en_US" >English</button>
    </form>
</ul>
</body>
</html>
