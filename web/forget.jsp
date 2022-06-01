<%-- 
    Khi bấm vào forgot password?
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="login.css" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forget Password</title>
    </head>
    <body>
        <c:choose>
            <c:when test ="${page == '1'}"> <!-- Lấy email -->
                <h1>We need your email to send confirmation code:</h1>
                <form action ="forget" method ="post">
                    <div id="message">
                        <span>${mess1}</span>
                    </div>
                    <label>Enter your email:</label>
                    <input type ="text" name ="email" value="${email}"><br>
                    <input type ="submit" value ="Ok">
                </form>
            </c:when>
            <c:when test ="${page == '2'}"> <!-- Lấy email -->
                <h1>We have sent an confirmation code to your email!</h1>
                <form action ="forget" method ="post">
                    <div id="message">
                        <span>${mess1}</span>
                    </div>
                    <label>Enter Confirmation Code:</label>
                    <input type ="hidden" name="email" value="${email}">
                    <input type ="hidden" name="page" value="2">
                    <input type ="text" name ="code" value=""><br>
                    <input type ="submit" value ="Ok">
                </form>
            </c:when>
            <c:when test ="${page == '3'}"><!-- Lấy email -->
                <h1>Now you can change your password!</h1>
                <form action ="forget" method ="post">
                    <input type ="hidden" name="email" value="${email}">
                    <input type ="hidden" name="page" value="3">
                    <div id="message">
                        <span>${mess2}</span>
                    </div>
                    <label>Enter New Password:</label><br>
                    <input type ="password" name ="password1" value=""><br>
                    <label>Confirm New Password:</label><br>
                    <input type ="password" name ="password2" value=""><br>
                    <input type ="submit" value ="Ok">
                </form>
            </c:when>
        </c:choose>
    </body>
</html>
