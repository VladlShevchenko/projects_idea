<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 24.10.2020
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr><th>Index</th><th>Name</th><th>Year</th><th>Price</th></tr>
    <c:set var="priceTotal" value="${0}" />
    <c:forEach var="publication" items="${publication}">
        <c:set var="priceTotal" value="${priceTotal + publication.priceForMonth}" />
        <tr>
            <td>${publication.id}</td>
            <td>${publication.name}</td>
            <td>2020</td>
            <td>${publication.priceForMonth}</td>
            <td> <button onclick="location.href='publicationView?command=deleteItem&publicationId=${publication.id}'">Cancel</button> </td>
        </tr>
    </c:forEach>
</table>
<form  action="publicaton" method="post" >
    <input type="hidden" name="command" value="publication"/>
    <button onclick="location.href='jsp/publications.jsp'">Continue shopping</button>
</form>
<p>Total price: ${priceTotal}</p>
<form  action="buy" method="post" >
    <input type="hidden" name="command" value="buy"/>
    <input type="hidden" name="amount" value=${priceTotal}/>
<button onclick="location.href='publicationView?command=account'">Buy</button>
</form>
</body>
</html>
