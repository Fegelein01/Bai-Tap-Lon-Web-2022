<%-- 
    Khi bấm vào delete => trang này hiện thông báo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="blog.css" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete</title>
    </head>
    <body>
        <h1>Are you sure you want to delete this post?</h1>
        <div id="para">
            Title: ${name}
        </div>
        <div id="para">
            Content: <br>
            &nbsp;&nbsp;&nbsp; ${ct}
        </div>
        <br>
        <form action="edit" method ="post" align ="center">
            <input type="hidden" name ="id" value="${id}">
            <input type="hidden" name ="content" value="a">
            <input type="submit" name ="action" value ="Delete">
            <input type="submit" name ="action" value ="Cancel">
        </form>
    </body>
</html>
