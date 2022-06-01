<%-- 
    Sau khi bấm vào control panel ở trang chính
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="styles/login.css" type="text/css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control Panel</title>
    </head>
    <body>
        <h1>Admin's Control Panel</h1>
        <form action ="admin" method ="post">
            <input type ="submit" name="action" value="Users List">
            <input type ="submit" name="action" value="Add/Remove Categories">
            <input type ="submit" name="action" value="Statistic">
        </form>
    </body>
    <!--Gộp các chức năng-->
    <c:choose> 
        <c:when test ="${function == 'Users List'}">
            <td>${mess1}</td>
            <table cellpadding="5" border=1>
                <tr valign="bottom">
                    <td align="left"><b>Username</b></td>     
                    <td align="left"><b>Name</b></td>
                    <td align="left"><b>Email</b></td>
                    <td align="left"><b>Role</b></td>
                </tr>
                <c:forEach items="${au}" var="user">
                    <tr valign="top">
                        <td>${user.username}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <c:choose>
                            <c:when test="${user.username == sessionScope.user.username}">
                                <td>${user.role}</td>
                                <td>You</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="redirecting?id=${user.id}&action=changeRole">${user.role}</a></td>
                                <td><a href="redirecting?id=${user.id}&action=deleteUser">Delete</a></td>
                            </c:otherwise>
                        </c:choose>

                    </tr>
                    
                </c:forEach>
            </table>
        </c:when>
        <c:when test ="${function == 'Add/Remove Categories'}">
            <td>${mess1}</td>
            <table cellpadding="5" border=1>
                <tr valign="bottom">
                    <td align="left"><b>Name</b></td>
                    <td align="left"><b>Note</b></td>
                </tr>
                <c:forEach items="${ac}" var="category">
                    <tr valign="top">
                        <td>${category.name}</td>
                        <td>${category.note}</td>
                        <td><a href="redirecting?id=${category.id}&action=editCategory">Edit</a></td>
                        <td><a href="redirecting?id=${category.id}&action=deleteCategory">Delete</a></td>
                    </tr>
                    
                </c:forEach>
            </table>
            <h2>Add a new category:</h2>
            <form action ="category" method ="post">
                <span>${mess2}</span>
                Category name:<br>
                <input type ="text" name ="name" required><br>
                Category note:<br>
                <input type ="text" name ="note" ><br>
                <input type ="submit" name="action" value="Add Category">
            </form>
        </c:when>
        <c:when test ="${function == 'Statistic'}">
            <form action="admin" method ="get">
                <label>Order by:</label><br>
                <input type ="radio" name ="action" value ="post date" checked>Post Date<br>
                <input type ="radio" name ="action" value ="last edit" >Last Edit<br>
                <input type ="radio" name ="action" value ="title" >Title<br>
                <input type ="radio" name ="action" value ="author" >Author<br>
                <input type ="radio" name ="action" value ="view" >View<br>
                <input type ="radio" name ="action" value ="like" >Like<br>
                <input type ="radio" name ="action" value ="dislike" >Dislike<br>
                <input type ="submit" name ="" value ="Ok"><br>
            </form>
            <table cellpadding="5" border=1>
                <tr valign="bottom">
                    <td align="left"><b>Title</b></td>
                    <td align="left"><b>Author</b></td>
                    <td align="left"><b>Post Date</b></td>
                    <td align="left"><b>Last Edit</b></td>
                    <td align="left"><b>View</b></td>
                    <td align="left"><b>Like</b></td>
                    <td align="left"><b>Dislike</b></td>
                </tr>
                <c:forEach items="${ab}" var="blog">
                    <tr valign="top">
                        <td><a href="detail?id=${blog.id}&action=view">${blog.title}</a></td>
                        <td>${blog.author}</td>
                        <td>${blog.postDate}</td>
                        <td>${blog.editDate}</td>
                        <td>${blog.view}</td>
                        <td>${blog.like}</td>
                        <td>${blog.dislike}</td>
                    </tr>
                    
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</html>


