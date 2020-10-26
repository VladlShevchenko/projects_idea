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
                <li><a onclick="location.href='publicationView?command=publication'"><fmt:message key="header.publications"/></a></li>
                <li><a onclick="location.href='cart?command=cart'"><fmt:message key="header.cart"/></a></li>
                <li><a onclick="location.href='account?command=account'"><fmt:message key="header.account"/></a></li>
                <li><a href="index.jsp"><fmt:message key="header.signIn"/></a></li>
            </ul>
        </div>

    </div>
</div>
<!-- *********  Header  ********** -->
    <c:forEach var="account" items="${account}">
        <%-- Share user to the command--%>
        <h2><fmt:message key="greetings"/> ${account.login}</h2> <br>
        <fmt:message key="account.email"/> ${account.email}      <br>
        <fmt:message key="account.bill"/> ${account.bill}           <a href=""><fmt:message key="account.top_up"/></a><br>
    </c:forEach>
<fmt:message key="account.subscriptions"/><br>
<table>
    <c:forEach var="publication" items="${publication}">
        <%-- Share name of publication to the command--%>
        <tr>
                    ${publication.id}
                    ${publication.name}
                    ${publication.priceForMonth}       <button onclick="location.href='deleteSubscribe?command=deleteSubscribe&publicationId=${publication.id}'"><fmt:message key="delete"/></button> <br>

        </tr>
    </c:forEach>
</table>

<br>
<br>
<form action="viewAccounts" method="post" >
    <input type="hidden" name="command" value="viewAccounts"/>
    <button><fmt:message key="admin.show_users"/></button>
</form>
<br>
<br>
<form action="logout" method="post" >
    <input type="hidden" name="command" value="logout"/>
    <button><fmt:message key="account.logout"/></button>
</form>

</body>
</html>