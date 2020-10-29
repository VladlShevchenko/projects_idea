<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 24.10.2020
  Time: 20:58
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<!-- *********  Header  ********** -->

<div id="header">
    <div id="header_in">

        <h1><a href="index.jsp"><b>Periodic</b> PUBLICATIONS</a></h1>

        <div id="menu">
            <ul>
                <li><h6><a onclick="location.href='publicationView?command=publication&currentPage=1'"><fmt:message key="header.publications"/></a></h6></li>
                <li><h6><a onclick="location.href='cart?command=cart'"><fmt:message key="header.cart"/></a></h6></li>
                <li><h6><a onclick="location.href='account?command=account'"><fmt:message key="header.account"/></a></h6></li>
                <li><h6><a href="index.jsp"><fmt:message key="header.signIn"/></a></h6></li>
            </ul>
        </div>

    </div>
</div>
<!-- *********  Header  ********** -->

    <c:forEach var="account" items="${account}">
        <%-- Share user to the command--%>
        <div class="mx-auto mt-3" style="width: 270px;">
        <h2><fmt:message key="greetings"/> ${account.login}</h2>
        </div>
        <div class="ml-5">
        <h5><fmt:message key="personal_data"/></h5>
        </div>
<div class="ml-5 mr-5">
    <table class="table table-hover" align="center">
        <th><h5><fmt:message key="account.login"/></h5></th><th> <h5><fmt:message key="email"/> </h5> </th><th><h5><fmt:message key="bill"/></h5></th><th><h5><fmt:message key="role"/></h5></th></tr>
        <td> ${account.login} </td>
        <td> ${account.email} </td>
        <td> ${account.bill}<br> <a><fmt:message key="account.top_up"/></a></td>
        <td> ${account.roleId}</td>
    </c:forEach>
    </table>
</div>

<div class="ml-5 mt-4 mr-5">
<h5><fmt:message key="account.subscriptions"/></h5>
<table class="table table-bordered">
    <th></th><th></th><th></th><th></th></tr>
    <c:forEach var="publication" items="${publication}">
        <%-- Share name of publication to the command--%>
        <tr>
            <td>   ${publication.id} </td>
            <td>   ${publication.name}</td>
            <td>   ${publication.priceForMonth}</td>
            <td><button class="btn btn-outline-dark" onclick="location.href='deleteSubscribe?command=deleteSubscribe&publicationId=${publication.id}'"><fmt:message key="delete"/></button> </td>
        </tr>
    </c:forEach>
</table>
</div>
<br>
<br>
<div class="float-right mr-5 mt-4 md-4">
<form action="viewAccounts" method="post" >
    <input type="hidden" name="command" value="viewAccounts"/>
    <button class="btn btn-warning btn-lg"><fmt:message key="admin.show_users"/></button>
</form>
</div>

    <div class="float-left ml-5 mt-4 md-4">
<form action="logout" method="post" >
    <input type="hidden" name="command" value="logout"/>
    <button class="btn btn-dark btn-lg"><fmt:message key="account.logout"/></button>
</form>
        <br>
        <br>
    </div>
</body>
</html>