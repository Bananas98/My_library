<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 25.09.2020
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login</title></head>

<body><h3>Login</h3>

<hr/>

<form name="loginForm" method="POST"

      action="controller">

    <input type="hidden" name="command" value="login"/>

    Login:<br/>

    <input type="text" name="login" value=""><br/>

    Password:<br/>

    <input type="password" name="password" value=""> <br/>

    <input type="submit" value="Enter">

</form>
<hr/>

</body>
</html>
