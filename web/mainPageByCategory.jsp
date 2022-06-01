<%-- 
   Trang chủ nhưng category thỏa mãn với category đã chọn

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta charset="UTF-8" />
<!DOCTYPE html>
<link rel="stylesheet" href="mainPage.css" type="text/css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
    </head>
    <body>
        <%@ taglib prefix="c"
                   uri="http://java.sun.com/jsp/jstl/core" %>
        <h1>Blog Đơn Giản</h1>


        <h2>Hello ${sessionScope.user.name}</h2>
        <!-- Phân quyền trên session, đó là role, hiện có 3: admin, mob, denied -->
        <!-- Đây là tùy vào role mà sẽ hiện ra nút gì, nếu role ko phải admin, mob (chưa đăng nhập) sẽ có nút đăng nhập -->

        <div id ="search">
            <c:choose>
                <c:when test ="${sessionScope.role == 'mob'}">
                    <form action ="redirecting" method="post">
                        <input type="submit" name="action" value ="View Profile">
                        <input type="submit" name="action" value ="Log Out">
                        <input type="submit" name="action" value ="Create a new post">
                    </form>
                </c:when>
                <c:when test ="${sessionScope.role == 'admin'}">
                    <form action ="redirecting" method="post">
                        <input type="submit" name="action" value ="View Profile">
                        <input type="submit" name="action" value ="Log Out">
                        <input type="submit" name="action" value ="Control Panel">
                        <input type="submit" name="action" value ="Create a new post">
                    </form>
                </c:when>
                <c:otherwise>
                    <form action ="redirecting" method="post">
                        <input type="submit" name="action" value ="Log In">
                        <input type="submit" name="action" value ="Register">
                    </form>
                </c:otherwise>
            </c:choose>
            <br>
            <form action="edit" method ="post">
                Search:
                <input type ="text" name="sTitle" required>
                <input type ="hidden" name ="action" value ="Search">
                <input type ="submit" value ="Search">
            </form>
        </div>


        <div class="left-div">
            Categories:
            <form action="" method="post">
                <table cellpadding="5" border=1>
                    <c:forEach items="${sessionScope.categories}" var="category">
                        <tr valign="top">
                            <td><a href="category?id=${category.id}">${category.name}</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </form> 
        </div>
        <div class="right-div">
        </form>
        <div id ="posts">
            <form action="" method="post">
                <c:forEach items="${ab}" var="blog">
                    <tr valign="top">
                    <div id="title">
                        <td><a href="detail?id=${blog.id}&action=view">${blog.title}</a></td>
                    </div>
                    <td>Đăng bởi:</td>
                    <td>${blog.author}</td><br>
                    <td>Lúc: </td>
                    <td>${blog.postDate}</td><br>
                    <td>Like: </td>
                    <td>${blog.like}</td><br>
                    <td>Dislike </td>
                    <td>${blog.dislike}</td><br>
                    <c:choose>
                        <c:when test ="${sessionScope.user.username == blog.username}">
                            <td><a href="edit?id=${blog.id}&action=edit">Edit</a></td>
                            <td><a href="edit?id=${blog.id}&action=delete">Delete</a></td>
                        </c:when>
                        <c:when test ="${sessionScope.role == 'admin'}">
                            <td><a href="edit?id=${blog.id}&action=delete">Delete</a></td>
                        </c:when>
                    </c:choose> 
                    </tr>
                    <br>
                </c:forEach>
            </form>
        </div>
        <br><br><br>


    </div>


</body>

<div id ="footer">
    <form action ="">
        <c:forEach items ="${pages}" var ="page">
            <a href="category?page=${page}&action=page">${page}</a>
        </c:forEach>
    </form>
</div>
</html>


<script>
    function handle(e) {
        if (e.keyCode === 13) {
            e.preventDefault(); // Ensure it is only this code that runs

            alert("Enter was pressed was presses");
        }
    }
</script>