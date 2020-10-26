<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 05.10.2020
  Time: 14:14
  To change this template use File | Settings | File Templates.
   <form  action="publicationView" method="post" >
        <jsp:useBean id="publication" type="java.util.List" scope="session" />
        <input type="hidden" name="command" value="publicationView"/>
        <%
            List<Publication> publications = (List<Publication>) request.getAttribute("publication");
            for(int i = 0; i < publication.size(); i+=1) { %>
        <tr>
            <td><%=publication.get(i).getName()%></td>
        </tr>
        <% } %>

    </form>
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
<h2><fmt:message key="publication.main"/></h2>
<form class="login100-form validate-form" action="search" method="post" >
    <input type="hidden" name="command" value="search"/>
<div class="wrap-input100 validate-input">
    <input class="input100" type="text" name="pubName" placeholder="<fmt:message key="search"/>" title="<fmt:message key="enter.pub_name"/>">
</div>
<button>
    <fmt:message key="search"/>
</button>
</form>
<table>
    <tr><th><fmt:message key="publication.name"/></th><th><fmt:message key="publication.price_for_mounth"/></th><th><fmt:message key="publication.description"/></th><th><fmt:message key="publication.topic"/></th></tr>
        <input type="hidden" name="command" value="publicationView"/>
    <div id="catalog">
        <div>
            <p><img src="images/category1.jpg" alt="" /></p>
            <p><a href="#">Видео</a> <span>1856</span></p>
        </div>
        <div>
            <p><img src="images/category2.jpg" alt="" /></p>
            <p><a href="#">Фото</a> <span>315</span></p>
        </div>
        <div>
            <p><img src="images/category3.jpg" alt="" /></p>
            <p><a href="#">Мобильные устройства</a> <span>2109</span></p>
        </div>
        <div>
            <p><img src="images/category4.jpg" alt="" /></p>
            <p><a href="#">Компьютеры и орг.техника</a> <span>4296</span></p>
        </div>
        <div>
            <p><img src="images/category5.jpg" alt="" /></p>
            <p><a href="#">Бытовая техника</a> <span>731</span></p>
        </div>
    </div>
    <img src="${pageContext.request.contextPath}/images/mainer.png"/>
    <c:forEach var="publication" items="${publication}">
        <%-- Share name of publication to the command--%>
    <div id="catalog" aria-orientation="horizontal">
        <div>
            <p><img src="${publication.image}" alt="" /></p>
            <p><a href="publicationView?command=publicationView&view=${publication.id}">${publication.name}</a> <span>1856</span></p>
        </div>
    </div>

        <tr><td><a href="publicationView?command=publicationView&view=${publication.id}">${publication.name}</a></td>
            <td>

                    ${publication.priceForMonth}
                    ${publication.description}
                    ${publication.topicId}
                <button onclick="location.href='publicationView?command=publicationView&view=${publication.id}'">
                    <fmt:message key="view_more"/>
                </button></td>
            </td>
        </tr>
    </c:forEach>

</table>
<ul>
    <li><a href="publicationView?command=publication&sessionLocale=en"><fmt:message key="english" /></a></li>
    <li><a href="publicationView?command=publication&sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
</ul>
</body>
</html>