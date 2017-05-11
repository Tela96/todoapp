<%--
  Created by IntelliJ IDEA.
  User: akos
  Date: 2017.05.10.
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo app register page</title>
</head>
<body>
<div>
    <h1>Welcome to the Todo web application</h1>
</div>

<div>
    <h2>Registration:</h2>
    <div>
        <form action="./register"method="post">
            <input name="username" type="text" required, placeholder="Your username" maxlength="32">
            <input type="submit">
        </form>
    </div>
</div>
<a href="./login.jsp">To the login screen!</a>

</body>
</html>
