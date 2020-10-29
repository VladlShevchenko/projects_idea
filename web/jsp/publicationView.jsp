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
        <table>
            <tr><th><fmt:message key="publication.name"/></th></tr>
    <c:forEach var="publication" items="${publication}">
       ${publication.name}
                ${pageContext.request.contextPath}${publication.image}
            ${publication.priceForMonth}
            ${publication.description}
            ${publication.topicId}

            <input type="hidden" name="command" value="cart"/>
            <input type="hidden" name="publication_id" value="${publication.id}"/>
                <c:if test="condition"></c:if>
            <c:choose>
            <c:when test="${statusId==1}">
            <button class="btn btn-warning btn-lg"><fmt:message key="publication.add_to_cart"/></button>
            </c:when>
            <c:otherwise>
            <button disabled="true" class="btn btn-warning btn-lg"><fmt:message key="publication.add_to_cart"/></button>
            </c:otherwise>
            </c:choose>

    </form>

</c:forEach>




</body>
</html>
