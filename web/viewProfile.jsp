<%-- 
Trang view Profile
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="styles/style1.css" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <h1>Your profile.</h1>
        <form action="profile" method="post">
            <span>${mess0}</span>
            <label>Name: </label>
            <input type ="text" name ="name" value="${sessionScope.user.name}" required>
            <input type="submit" name="action" value="Change Name"><br>
            <span>${mess01}</span>
            <label>Email: </label>
            <input type ="text" name ="email" value="${sessionScope.user.email}" required>
            <input type="submit" name="action" value="Change Email"><br>
            <label>Username: </label>
            <span>${sessionScope.user.username}</span><br>
        </form>
        <h3>If you want to change your password.</h3><br>
        <form action="profile" method="post">
            
            <span>${mess1}</span>
            <label>Enter your password:</label>
            <input type ="password" name ="password"><br>
            <span>${mess2}</span>
            <label>Enter your new password:</label>
            <input type ="password" name ="nPassword1"><br>
            <label>Confirm your new password:</label>
            <input type="password" name ="nPassword2"><br>
            <input type="submit" name="action" value="Change Password"><br>
        </form>
    </body>
</html>
