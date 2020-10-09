<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 09.10.2020
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Welcome
<c: var="account" items="${account}">
    <tr><td>${account.login}</td>
    </tr>
</c:>
</body>
</html>
