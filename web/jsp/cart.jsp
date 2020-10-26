<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 24.10.2020
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>

<html lang="${sessionScope.lang}">
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

<div id="header">
    <div id="header_in">

        <h1><a href="index.jsp"><b>Periodic</b> PUBLICATIONS</a></h1>

        <div id="menu">
            <ul>
                <li><a onclick="location.href='publicationView?command=publication'"><fmt:message key="header.publications"/></a></li>
                <li><a onclick="location.href='cart?command=cart'"><fmt:message key="header.cart"/></a></li>
                <li><a onclick="location.href='account?command=account'"><fmt:message key="header.account"/></a></li>
                <li><a href="index.jsp"><fmt:message key="header.signIn"/></a></li>
            </ul>
        </div>

    </div>
</div>

<table>
    <tr><th><fmt:message key="account.id"/>    </th><th>   <fmt:message key="publication.name"/>    </th><th>    <fmt:message key="publication.year"/>    </th><th>   <fmt:message key="publication.price_for_mounth"/>   </th></tr>
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
    <c:choose>
        <c:when test="${priceTotal==0}">
            <button disabled="true"><fmt:message key="buy"/></button>
        </c:when>
        <c:otherwise>
            <button onclick="location.href='publicationView?command=account'"><fmt:message key="buy"/></button>
        </c:otherwise>
    </c:choose>

</form>
</body>
</html>
