<%-- 
    Sau khi bấm vào nút Change Email trong View Profile => hiện ra trang này
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Change</title>
    </head>
    <body>
        <h1>Are you sure you want to change your email into: </h1>
        <span>${email}?</span>
        <form action="profile" method="post">
            <input type ="hidden" name="email" value="${email}">
            <input type="submit" name="action" value="Confirm "><br>
            <input type="submit" name="action" value="Cancel"><br>
        </form>
    </body>
</html>
