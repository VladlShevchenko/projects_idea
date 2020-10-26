<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 25.10.2020
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="style/layout.css">
    <link rel="stylesheet" type="text/css" href="style/review.css">
</head>
<body>
<!-- *********  Header  ********** -->

<div id="header">
    <div id="header_in">

        <h1><a href="index.jsp"><b>Periodic</b> PUBLICATIONS</a></h1>

        <div id="menu">
            <ul>
                <li><a class="active">Home</a></li>
                <li><a onclick="location.href='publicationView?command=publication'">Publications</a></li>
                <li><a href="ourwork.html">Our work</a></li>
                <li><a onclick="location.href='account?command=account'">Account</a></li>
                <li><a href="login.html">Sign In</a></li>
            </ul>
        </div>

    </div>
</div>
<table>
    <tr><th>Id</th><th>Login</th><th>Email</th><th>Bill</th><th>Role</th></tr>
    <form  action="cart" method="post" >
        <c:forEach var="account" items="${account}">
            <tr><td>${account.id}</td>
                <td>${account.login}</td>
                <td>${account.email}</td>
                <td>${account.bill}</td>
                <td>${account.roleId}</td>
                <td>  <button>Block</button></td></td>
            </tr>
        </c:forEach>
    </form>
</table>
</body>
</html>