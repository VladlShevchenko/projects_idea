<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 27.09.2020
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
<title>Accounts</title>
</head>
<body>
<h2>Users List</h2>
<table>
    <tr><th>Login</th><th>Email</th><th>Bill</th><th>Role</th></tr>

    <c:forEach var="account" items="${account}">
        <tr><td>${account.login}</td>
            <td>${account.email}</td>
            <td>${account.bill}</td>
            <td>${account.roleId}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
