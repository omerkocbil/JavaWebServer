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
    <form>
        <inputText value=""/>
        <commandButton value="Start Server" action="#{server.start}"></commandButton>
        <commandButton value="Stop Server" action="#{server.stop}"></commandButton>
    </form>
</body>
</html>
