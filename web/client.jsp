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
    <form>
        <inputText value=""/>
        <inputText value=""/>
        <inputText value="" binding="#{message}"/>
        <commandButton value="Connect" action="#{clientPrev.start}"></commandButton>
        <commandButton value="Disconnect" action="#{clientPrev.disconnect}"></commandButton>
        <commandButton value="Send Message" action="#{clientPrev.sendMessage(message.value)}"></commandButton>
    </form>
</body>
</html>
