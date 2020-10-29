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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>

<div id="header">
    <div id="header_in">

        <h1><a href="index.jsp"><b>Periodic</b> PUBLICATIONS</a></h1>

        <div id="menu">
            <ul>
                <li><a onclick="location.href='publicationView?command=publication&currentPage=1'"><fmt:message key="header.publications"/></a></li>
                <li><a onclick="location.href='cart?command=cart'"><fmt:message key="header.cart"/></a></li>
                <li><a onclick="location.href='account?command=account'"><fmt:message key="header.account"/></a></li>
                <li><a href="index.jsp"><fmt:message key="header.signIn"/></a></li>
            </ul>
        </div>

    </div>
</div>
<div class="mr-5 ml-5 mt-3">
<table class="table table-striped">
    <tr><th><h6><fmt:message key="account.id"/>  </h6>  </th><th><h6>   <fmt:message key="publication.name"/> </h6>   </th><th> <h6>   <fmt:message key="publication.year"/>  </h6>  </th><th><h6>   <fmt:message key="publication.price_for_mounth"/>  </h6> </th><th></th></tr>
    <c:set var="priceTotal" value="${0}" />
    <c:forEach var="publication" items="${publication}">
        <c:set var="priceTotal" value="${priceTotal + publication.priceForMonth}" />
        <tr>
            <td>${publication.id}</td>
            <td>${publication.name}</td>
            <td>2020</td>
            <td>${publication.priceForMonth}</td>
            <td> <button onclick="location.href='publicationView?command=deleteItem&publicationId=${publication.id}'"><fmt:message key="publication.remove"/></button> </td>
        </tr>
    </c:forEach>
</table>
<div class="float-right mr-5">
<h5><fmt:message key="total_price"/> ${priceTotal}</h5>
</div>
<br><br>
<form  action="publicaton" method="post" >
    <input type="hidden" name="command" value="publication"/>
    <input type="hidden" name="currentPage" value="1"/>
    <div class="float-left ml-4">
    <button onclick="location.href='jsp/publications.jsp'" class="btn btn-outline-primary btn-lg"><fmt:message key="continue_shopping"/></button>
    </div>
</form>
<form  action="buy" method="post" >
    <input type="hidden" name="command" value="buy"/>
    <input type="hidden" name="amount" value=${priceTotal}/>
    <c:choose>
        <c:when test="${priceTotal==0}">
        <div class="float-right mr-5">
            <button disabled="true" class="btn btn-warning btn-lg"><fmt:message key="buy"/></button>
        </div>
        </c:when>
        <c:otherwise>
        <div class="float-right mr-5">
            <button onclick="location.href='publicationView?command=account'" class="btn btn-warning btn-lg"><fmt:message key="buy"/></button>
        </div>
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
