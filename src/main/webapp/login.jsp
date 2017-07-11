<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 10.07.2017
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="login" method="post">
        <h3>Name</h3>
        <input id="username" name="username" type="text" />
        <h3>Password</h3>
        <input id="password" name="password" type="password" /><br />
        <button type="submit" name="Login" value="Login" title="Login">Login</button>
    </form>
</body>
</html>
