<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 24.10.2020
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Publications</title>
    <link rel="stylesheet" type="text/css" href="style/layout.css">
    <link rel="stylesheet" type="text/css" href="style/review.css">
    <link rel="stylesheet" type="text/css" href="style/util.css">
    <link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>
<!-- *********  Header  ********** -->

<div id="header">
    <div id="header_in">

        <h1><a href="index.jsp"><b>Periodic</b> PUBLICATIONS</a></h1>

        <div id="menu">
            <ul>
                <li><a onclick="location.href='publicationView?command=publication'">Publications</a></li>
                <li><a onclick="location.href='cart?command=cart'">Cart</a></li>
                <li><a onclick="location.href='account?command=account'">Account</a></li>
                <li><a href="login.html">Sign In</a></li>
            </ul>
        </div>

    </div>
</div>
<c:forEach var="account" items="${account}">
    <%-- Share user to the command--%>
    <h2>Hello, ${account.login}</h2> <br>
    Your email: ${account.email}<br>
    Your bill: ${account.bill}           <a href="">Top up</a><br>
</c:forEach>
Your subscriptions:<br>
<table>
    <c:forEach var="publication" items="${publication}">
        <%-- Share name of publication to the command--%>
        <tr>
                ${publication.id}
                ${publication.name}
                ${publication.priceForMonth}       <button onclick="location.href='deleteSubscribe?command=deleteSubscribe&publicationId=${publication.id}'">Delete</button> <br>
            </td>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="logout" method="post" >
    <input type="hidden" name="command" value="logout"/>
    <button>Logout</button>
</form>
</body>
</html>
