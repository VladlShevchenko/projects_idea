<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 27.09.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>EPAM</title>
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
        <li><a href="index.jsp" class="active">Home</a></li>
        <li><a href="/jsp/add.jsp">About</a></li>
        <li><a href="ourwork.html">Our work</a></li>
        <li><a href="blog.html">Blog</a></li>
        <li><a href="login.html">Sign In</a></li>
      </ul>
    </div>

  </div>
</div>

<div>
  <h1>Super app!</h1>
</div>

<div>
  <div>
    <button onclick="location.href='login.html'">login </button>
    <button onclick="location.href='/add'">Add user</button>
    <button onclick="location.href='signIn.html'">View publications</button>
    <input type="hidden" name="command" value="publication"/>
  </div>
</div>
</body>
</html>
