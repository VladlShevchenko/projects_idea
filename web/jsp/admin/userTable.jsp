<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 25.10.2020
  Time: 18:47
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
    <title>User Table</title>
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
                <li><a onclick="location.href='publicationView?command=publication'"><fmt:message key="header.publications"/></a></li>
                <li><a onclick="location.href='cart?command=cart'"><fmt:message key="header.cart"/></a></li>
                <li><a onclick="location.href='account?command=account'"><fmt:message key="header.account"/></a></li>
                <li><a href="index.jsp"><fmt:message key="header.signIn"/></a></li>
            </ul>
        </div>

    </div>
</div>
<table>
    <tr><th><fmt:message key="account.id"/> </th><th><fmt:message key="account.login"/> </th><th> <fmt:message key="email"/> </th><th> <fmt:message key="bill"/> </th><th> <fmt:message key="role"/> </th></tr>
    <form  action="cart" method="post" >
        <c:forEach var="account" items="${account}">
            <tr><td>${account.id}</td>
                <td>${account.login}</td>
                <td>${account.email}</td>
                <td>${account.bill}</td>
                <td>${account.roleId}</td>
                <td>
                    <!-- *********  Buttons Block\Unblock  ********** -->
                    <c:choose>
                        <c:when test="${account.roleId==2}">
                            <form  action="blockUser" method="post" >
                                <input type="hidden" name="command" value="blockUser"/>
                                <input type="hidden" name="userId" value="${account.id}"/>
                                <button><fmt:message key="block"/></button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form  action="unblockUser" method="post" >
                                <input type="hidden" name="command" value="unblockUser"/>
                                <input type="hidden" name="userId" value="${account.id}"/>
                                <button><fmt:message key="unblock"/></button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </form>
</table>
</body>
</html>