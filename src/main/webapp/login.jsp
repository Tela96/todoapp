<%--
  Created by IntelliJ IDEA.
  User: akos
  Date: 2017.05.10.
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo app login page</title>
</head>
<body>
<div>
    <h1>Please log in using your username below</h1>
</div>
<div>
    <form action="./login" method="get">
        <input  name="name" type="text" required maxlength="32" placeholder="Your username here">
        <input type="submit">
    </form>
</div>

</body>
</html>
