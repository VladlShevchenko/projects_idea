<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<%@ page session="true" %>


<html lang="${sessionScope.lang}">
<head>
    <title>Login V2</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="style/util.css">
    <link rel="stylesheet" type="text/css" href="style/main.css">
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form" action="signIn" method="post" >

                <input type="hidden" name="command" value="signIn"/>
                <span class="login100-form-title p-b-5">
						Create your account
					</span>
                <span class="login100-form-title p-b-28">
						<i class="zmdi zmdi-font"></i>
					</span>
                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="userlogin" placeholder="Login" title="<fmt:message key="enter_login" />">
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c" >
                    <input class="input100" type="text" name="username" placeholder="Email" title="<fmt:message key="enter_email" />"">

                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
                    <input class="input100" type="password" name="userpass" placeholder="Password" title="<fmt:message key="enter_password" />"">
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn">
                            <fmt:message key="sign_up" />"
                        </button>
                    </div>
                </div>

                <div class="text-center p-t-75">
						<span class="txt1">
                        <fmt:message key="already_registered" />"
						</span>

                    <a class="txt2" href='login.jsp'>
                        <fmt:message key="account.signIn" />"
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
