<%-- 
    Trang login
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="login.css" type="text/css"/>
<!DOCTYPE html>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${login == 'login'}">
                <h1>Login</h1>
            </c:when>
            <c:when test="${login == 'register'}">
                <h1>Register</h1>
            </c:when>
        </c:choose>
        <form action ="login" method ="post">
            <input type ="hidden" name ="login" value="${login}">
            <!--<span>${sessionScope.validation.get(0)}</span>-->
            <div id="message">
                <span>${mess1}</span>
            </div>
            <label>Username:</label>
            <input type ="text" name ="username" value="${username}"><br>

            <c:choose>
                <c:when test = "${login == 'register'}">
                    <div id="message">
                        <span>${mess2}</span>
                    </div>
                    <label>Name:</label>
                    <input type ="text" name ="name" value="${name}"><br>
                    <div id="message">
                        <span>${mess5}</span>
                    </div>
                    <label>Email:</label>
                    <input type ="text" name ="email" value="${email}"><br>
                </c:when>
            </c:choose>
            <div id="message">
                <span>${mess2}</span>
            </div>
            <label>Password:</label>
            <input type ="password" name="password" value =""><br>
            <c:choose>
                <c:when test="${login == 'login'}">
                    <input type ="submit" name ="action" value="Login">
                    <td><a href="forget">Forgot password?</a></td>
                </c:when>
                <c:when test="${login == 'register'}">
                    <div id="message">
                        <span>${mess4}</span>
                    </div>
                    <label>Confirm Password:</label>
                    <input type ="password" name="password2" value =""><br>
                    <input type ="submit" name ="action" value="Register">
                </c:when>
            </c:choose>
        </form>
    </body>
</html>
