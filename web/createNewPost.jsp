<%-- 
    Tạo post mới, đồng thời edit post
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="blog.css" type="text/css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new post</title>
    </head>
    <body>
        <%@ taglib prefix="c"
                   uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
        <!--edit là tên servlet của BlogServlet, chưa đổi-->
        <form action ="edit" method ="post">
            <input type ="hidden" name ="author" value ="${sessionScope.user.name}">
            <input type ="hidden" name ="username" value ="${sessionScope.user.username}">
            <input type ="hidden" name ="id" value ="${b.id}">
            Title:<br>
            <input type ="text" name ="title" value ="${b.title}" style="width: 1190px;">
            <br>Content:<br>
            <textarea name="content" cols="160" rows="20">${b.content}</textarea><br>
            <div id="ita">Category:</div><br>
            <c:forEach items="${sessionScope.categories}" var="category">
                <tr valign="top">
                    <c:choose>
                        <c:when test='${fn:contains(b.categories, category.id)}'>
                        <input type ="checkbox" name ="categories" value ="${category.id}" checked>${category.name}<br>
                    </c:when>
                    <c:otherwise>
                        <input type ="checkbox" name ="categories" value ="${category.id}">${category.name}<br>
                    </c:otherwise>
                </c:choose>
                </tr>
            </c:forEach>
            <c:choose>
                <c:when test="${create == 'true'}">
                    <input type ="submit" name ="action" value ="Post"> <!-- Nút để tạo post mới -->
                </c:when>
                <c:otherwise>
                    <input type ="submit" name ="action" value ="Ok"> <!-- Nút để tạo edit post -->
                </c:otherwise>
            </c:choose>
            <input type ="submit" name ="action" value ="Cancel">
        </form>
    </body>
</html>
