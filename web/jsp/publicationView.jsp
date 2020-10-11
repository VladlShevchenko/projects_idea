<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 09.10.2020
  Time: 14:52
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
                <li><a href="index.jsp" class="active">Home</a></li>
                <li><a href="/jsp/add.jsp">About</a></li>
                <li><a href="ourwork.html">Our work</a></li>
                <li><a href="blog.html">Blog</a></li>
                <li><a href="login.html">Sign In</a></li>
            </ul>
        </div>

    </div>
</div>
<table>
    <tr><th>Login</th><th></th><th></th><th></th></tr>

    <c:forEach var="publication" items="${publication}">
        <tr><td>${publication.name}</td>
            <td>${publication.priceForMonth}</td>
            <td>${publication.description}</td>
            <td>${publication.topicId}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
