<%--
  Created by IntelliJ IDEA.
  User: jan
  Date: 10.03.2017
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Server Form</title>
</head>
<body>
    <form action = "/server/start" method="get">
        <input type="text" value="" name="port"/>
        <input type="submit" value="Start Server" />
    </form>
    <form action = "/server/stop" method="get">
        <input type="submit" value="Stop Server" />
    </form>
</body>
</html>
