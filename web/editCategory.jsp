<%-- 
Sau khi click edit trong category ở control panel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="blog.css" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Category</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${action=='editCategory'}">
                <form action ="category" method ="post">
                    <input type="hidden" name="id" value="${category.id}">
                    <label>Category name:</label>
                    <input type="text" name ="name" value="${category.name}"><br>
                    <label>Note:</label>
                    <input type="text" name ="note" value="${category.note}"><br>
                    <input type="submit" name ="action" value="Change">
                </form>
            </c:when>
            <c:when test="${action=='deleteCategory'}">
                <form action ="category" method ="post">
                    <label>Are you sure that you want to delete this category?</label><br>
                    <input type="hidden" name="id" value="${category.id}">
                    <span>${category.name}</span><br>
                    <span>${category.note}</span><br>
                    <input type="submit" name ="action" value="Delete">
                </form>
            </c:when>
        </c:choose>
    </body>
</html>
