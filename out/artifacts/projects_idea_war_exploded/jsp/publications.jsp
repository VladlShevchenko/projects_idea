<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 05.10.2020
  Time: 14:14
  To change this template use File | Settings | File Templates.

--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "custom" uri = "/WEB-INF/customTag.tld"%>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>

<html lang="${sessionScope.lang}">
<html>
<head>
    <meta charset="UTF-8">
    <title>Publications</title>
    <style type="text/css">
        BODY { font: 10pt Arial, Helvetica, sans-serif; }
        #catalog A { color: #666; }
        #catalog A:hover { color: #1fa0e2; }
        #catalog DIV {
            width: 210px; /* Ширина */
            margin: 0 5px 15px 0; /* Отступы */
            text-align: center; /* Выравнивание по центру */
            display: inline-block; /* Строчно-блочный элемент */
            vertical-align: top; /* Выравнивание по верхнему краю */
        }
        #catalog P { margin: 0 5px; }
        #catalog SPAN { color: #ccc; font-size: 0.8em; }
    </style>
    <link rel="stylesheet" type="text/css" href="style/layout.css">
    <link rel="stylesheet" type="text/css" href="style/review.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>
<!-- *********  Header  ********** -->

<div id="header">
    <div id="header_in">
        <%-- Custom Tag--%>
        <h1><a href="index.jsp"><b>Periodic</b> <custom:Hello message = "PUBLICATIONS" /></a></h1>

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
<div class="ml-5">
<h2><fmt:message key="publication.main"/></h2>
</div>
<form action="sortByTopic" method="post">
    <input type="hidden" name="command" value="sortByTopic"/>
    <div class="float-left ml-5 mt-2">
<select class="selectpicker" name ="topicId">
    <c:forEach var="topic" items="${topic}">
        <option value = "${topic.id}">${topic.name}</option>
    </c:forEach>
</select>
    <input type="submit" class="btn btn-warning btn-sm" value=<fmt:message key="search"/>>
    </div>
</form>

<form action="sortByParam" method="post">
    <input type="hidden" name="command" value="sortByParam"/>
    <div class="float-right mr-5 mt-2">
    <select class="selectpicker" name ="paramId">
            <option value = "1"><fmt:message key="sort.a_z"/></option>
            <option value = "2"><fmt:message key="sort.z_a"/></option>
            <option value = "3"><fmt:message key="sort.cheap_expensive"/></option>
            <option value = "4"><fmt:message key="sort.expensive_cheap"/></option>
            <option value = "5"><fmt:message key="default"/></option>
    </select>
    <input type="submit" class="btn btn-warning btn-sm" value=<fmt:message key="search"/>>
    </div>
</form>

<br><br>
<div class="float-left ml-5">
<form class="login100-form validate-form" action="search" method="post" >
    <input type="hidden" name="command" value="search"/>
<div class="wrap-input100 validate-input">
    <input class="input100" type="text" name="pubName" placeholder="<fmt:message key="search"/>" title="<fmt:message key="enter.pub_name"/>">
</div>
<button class="btn btn-warning mt-2 btn-sm">
    <fmt:message key="search"/>
</button>
</form>
</div>
<br><br>
<div class="float-left ml-5 mr-5">
<table class="table table-hover mt-2">
    <tr><th><fmt:message key="publication.name"/></th><th><fmt:message key="publication.price_for_mounth"/></th><th><fmt:message key="publication.description"/></th><th><fmt:message key="publication.topic"/></th><th></th></tr>
        <input type="hidden" name="command" value="publicationView"/>

    <c:forEach var="publication" items="${publication}">
        <%-- Share name of publication to the command--%>
        <tr><td><a href="publicationView?command=publicationView&view=${publication.id}">${publication.name}</a></td>

            <td> ${publication.priceForMonth}</td>
            <td> ${publication.description}</td>
            <td> ${publication.topicId}</td>
            <td> <button class="btn btn-outline-primary" onclick="location.href='publicationView?command=publicationView&view=${publication.id}'">
                    <fmt:message key="view_more"/>
                </button></td>
            </td>
        </tr>
    </c:forEach>

</table>

<%-- Pagination--%>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="publicationView?command=publication&recordsPerPage=5&currentPage=1">1</a></li>
        <li class="page-item"><a class="page-link" href="publicationView?command=publication&recordsPerPage=5&currentPage=2">2</a></li>
        <li class="page-item"><a class="page-link" href="publicationView?command=publication&recordsPerPage=5&currentPage=3">3</a></li>
    </ul>
</nav>


<ul>
    <li><a href="publicationView?command=publication&sessionLocale=en"><fmt:message key="english" /></a></li>
    <li><a href="publicationView?command=publication&sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
</ul>
</div>
</body>
</html>