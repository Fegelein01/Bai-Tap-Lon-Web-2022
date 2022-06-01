<%-- 
    Sau khi click link href delete trong control panel phần user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete</title>
    </head>
    <body>
        <c:choose>
            <c:when test ="${delete=='User'}">
                <h1>Are you sure you want to delete this user?</h1>
            Username: 
            <td>${user.username}</td><br>
            Name:
            <td>${user.name}</td><br>
            Email:
            <td>${user.email}</td><br>
            Role:
            <td>${user.role}</td><br>
            <form action ="admin" method ="post">
                <input type ="hidden" name ="userid" value ="${user.id}">
                <input type="submit" name ="deleteAction" value ="Yes">
                <input type="submit" name ="deleteAction" value ="No">
            </form>
        </c:when>
    </c:choose>
</body>
</html>
