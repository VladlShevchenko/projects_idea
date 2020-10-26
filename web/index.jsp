<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 27.09.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>


<html lang="${sessionScope.lang}">

<head>
  <title>Login</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="style/util.css">
    <link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div class="limiter">
<div class="container-login100">
<div class="wrap-login100">
  <form class="login100-form validate-form" action="login" method="post" >

    <input type="hidden" name="command" value="login"/>
    <span class="login100-form-title p-b-26">
                    <fmt:message key="welcome" />
                </span>
    <span class="login100-form-title p-b-48">
                    <i class="zmdi zmdi-font"></i>
                </span>

    <div class="wrap-input100 validate-input" >
      <input class="input100" type="text" name="username" placeholder="<fmt:message key="enter_login" />" title=<fmt:message key="enter_login" />>

    </div>

    <div class="wrap-input100 validate-input" data-validate="Enter password">
                    <span class="btn-show-pass">
                        <i class="zmdi zmdi-eye"></i>
                    </span>
      <input class="input100" type="password" name="userpass" placeholder="<fmt:message key="enter_password" />" title=<fmt:message key="enter_password" />>
    </div>

      <div class="container-login100-form-btn">
          <div class="wrap-login100-form-btn">
              <div class="login100-form-bgbtn"></div>
              <button class="login100-form-btn">
                  <fmt:message key="account.login"/>
              </button>
          </div>
      </div>
    <div class="text-center p-t-115">
                    <span class="txt1">
                       <fmt:message key="account.login"/>
                    </span>

      <a class="txt2" href='signIn.jsp'>
          <fmt:message key="sign_up" />
      </a>
    </div>

  </form>
    <form  action="publicaton" method="post" >
        <input type="hidden" name="command" value="publication"/>
        <button>
            <fmt:message key="guest" />
        </button>
    </form>
</div>
</div>
</div>
<ul>
    <li><a href="?sessionLocale=en"><fmt:message key="english" /></a></li>
    <li><a href="?sessionLocale=uk"><fmt:message key="ukrainian" /></a></li>
</ul>
</body>
</html>
