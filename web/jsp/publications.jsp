<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 05.10.2020
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Publications</title>
</head>
<body>
<h2>Users List</h2>
<table>
    <tr><th>Name</th><th>Price</th><th>Description</th><th>Topic</th></tr>

    <c:forEach var="publication" items="${publication}">
        <tr><td><a href="index.jsp">${publication.name}</a></td>
            <td>${publication.priceForMonth}</td>
            <td>${publication.description}</td>
            <td>${publication.topicId}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>