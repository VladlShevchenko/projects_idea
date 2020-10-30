<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 09.10.2020
  Time: 14:52
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
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="style/layout.css">
    <link rel="stylesheet" type="text/css" href="style/review.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style>
        .leftimg {
            float:left; /* Выравнивание по левому краю */
            margin: 15px 15px 15px 0; /* Отступы вокруг картинки */
        }
    </style>
</head>
<body>
<!-- *********  Header  ********** -->

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


    <form  action="cart" method="post" >

    <c:forEach var="publication" items="${publication}">
            <div class=" mx-auto mt-3 " style="width: 330px;">
                <h2 class="text-warning">${publication.name}</h2>
            </div>

            <div class="ml-5 mt-4">
                <img src="${publication.image}" style="width: 240px;" class="leftimg"/>
                <h5><fmt:message key="publication.id"/>  ${publication.id}</h5>
                <h5><fmt:message key="publication.description"/>:</h5>
                <h5 class="text-secondary">${publication.description}</h5>
                <h5><fmt:message key="publication.lg"/></h5>
                <div class="float-right mr-5">
                    <h5 class="text-warning"> 2020</h5>
                </div>

                <h5><fmt:message key="publication.year"/></h5>
                <br><br><br><br><br><br>
                <div class=" mr-5 mt-3">
                    <h5><fmt:message key="publication.price_for_mounth"/> ${publication.priceForMonth}</h5>
                </div>
            </div>

            <input type="hidden" name="command" value="cart"/>
            <input type="hidden" name="publication_id" value="${publication.id}"/>
                <c:if test="condition"></c:if>
            <c:choose>
            <c:when test="${statusId==1}">
            <div class="float-right mr-5">
            <button class="btn btn-warning btn-block"><fmt:message key="publication.add_to_cart"/></button>
            </div>
            </c:when>
            <c:otherwise>
            <div class="float-right mr-5">
            <button disabled="true" class="btn btn-warning btn-lg"><fmt:message key="publication.add_to_cart"/></button>
            </div>
            </c:otherwise>
            </c:choose>
</c:forEach>
    </form>


</body>
</html>
