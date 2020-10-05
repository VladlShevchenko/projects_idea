<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Login Page</title>
</head>

<body>
<form action="login" method="post">
    <input type="hidden" name="command" value="login"/>
    Name:<input type="text" name="username"/><br/><br/>
    Password:<input type="password" name="userpass"/><br/><br/>
    <input type="submit" value="login" >
</form>
</body>
</html>