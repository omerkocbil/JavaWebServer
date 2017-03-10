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
    <title>Client Form</title>
</head>
<body>
    <form action = "/client/start" method="get">
        <input type="text" value=""  name="ip"/>
        <input type="text" value="" name="port"/>
        <input type="submit" value="Connect" />
    </form>
    <form action = "/client/stop" method="get">
        <input type="submit" value="Disconnect" />
    </form>
    <form action = "/client/send" method="get">
        <input type="text" value="" name="message"/>
        <input type="submit" value="Send Message" />
    </form>
</body>
</html>
