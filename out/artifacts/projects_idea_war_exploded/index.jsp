<%--
  Created by IntelliJ IDEA.
  User: Vl
  Date: 27.09.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Login</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="style/main.css">
  <link rel="stylesheet" type="text/css" href="style/util.css">
</head>
<body>

<div class="limiter">
  <div class="container-login100">
    <div class="wrap-login100">
      <form class="login100-form validate-form" action="login" method="post" >

        <input type="hidden" name="command" value="login"/>
        <span class="login100-form-title p-b-26">
						Welcomes
					</span>
        <span class="login100-form-title p-b-48">
						<i class="zmdi zmdi-font"></i>
					</span>

        <div class="wrap-input100 validate-input" data-validate = "Valid email is: a@b.c" >
          <input class="input100" type="text" name="username" placeholder="Email" title="Enter email here">

        </div>

        <div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
          <input class="input100" type="password" name="userpass" placeholder="Password" title="Enter password here">
        </div>

        <div class="container-login100-form-btn">
          <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
                  <button>
                      Login
                  </button>
          </div>
        </div>
        <div class="text-center p-t-115">
						<span class="txt1">
							Don’t have an account?
						</span>

          <a class="txt2" href='signIn.html'>
            Sign Up
          </a>
        </div>

      </form>
        <form  action="publicaton" method="post" >
            <input type="hidden" name="command" value="publication"/>
            <button>
               Or continue as a guest
            </button>
        </form>
    </div>
  </div>
</div>
</body>
</html>
