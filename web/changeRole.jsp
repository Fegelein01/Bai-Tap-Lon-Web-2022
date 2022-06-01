<%-- 
    Sau khi bấm vào link href "admin" hoặc "mob" trên controlpanel => hiện ra radio chọn role
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c"
                   uri="http://java.sun.com/jsp/jstl/core" %>
        <title>Change Role</title>
    </head>
    <body>
        <h1>Please select a role:</h1>
        <form action ="admin" method="post">
            <c:forEach items="${roles}" var="r">
                <c:choose>
                    <c:when test='${r == user.role}'>
                        <input type ="radio" name ="role" value ="${r}" checked>${r}<br>
                    </c:when>
                    <c:otherwise>
                        <input type ="radio" name ="role" value ="${r}">${r}<br>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <input type ="hidden" name ="id" value="${user.id}">
            <br><input type ="submit" name ="action" value="Ok">
        </form>
    </body>
</html>
