<%-- 
    Hiển thị chi tiết của 1 post, có title, nội dung, các comment, số like, dislike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="blog.css" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.currentBlog.title}</title>
    <h2>Created: ${sessionScope.currentBlog.postDate}</h2>
    <h2>Last Edited: ${sessionScope.currentBlog.editDate}</h2>
</head>
<body>
    <%@ taglib prefix="c"
               uri="http://java.sun.com/jsp/jstl/core" %>
    <h1>${sessionScope.currentBlog.title}</h1><br>
    <div id="para">${sessionScope.currentBlog.content}</div>
    <div id ="vote">
        <c:choose>
            <c:when test ="${sessionScope.role == 'guest'}">

                <p>Up Vote: ${sessionScope.currentBlog.like} &nbsp &nbsp Down Vote: ${sessionScope.currentBlog.dislike}</p><br>
            </c:when>
            <c:otherwise>
                <p><a href="detail?id=${sessionScope.postId}&action=like">Up Vote</a>: ${sessionScope.currentBlog.like}
                    &nbsp &nbsp <a href="detail?id=${sessionScope.postId}&action=dislike">Down Vote</a>: ${sessionScope.currentBlog.dislike}</p><br>
                </c:otherwise>
            </c:choose>
    </div>
    <div id="ita">Comment: ${mess1}</div><br>


    <div id="comment">
        <form action="detail" method="post">
            <c:forEach items="${sessionScope.currentComments}" var="comment">
                <c:choose>
                    <c:when test = "${comment.parentId == -1}">
                        <tr valign="top">
                            <td>${comment.author} - ${comment.postDate}${comment.state}:</td><br>
                        <div id="comment2">${comment.content}</div><br>
                        <c:choose>
                            <c:when test ="${comment.username == sessionScope.user.username}">
                                <td><a href="detail?commentId=${comment.id}&action=edit1">Edit</a></td>
                                <td><a href="detail?id=${comment.id}&action=delete">Delete</a></td>
                                <td><a href="detail?id=${comment.postId}&replyId=${comment.id}&action=reply2">Reply</a></td>
                            </c:when>
                            <c:when test="${sessionScope.role  == 'admin'}">
                                <td><a href="detail?id=${comment.id}&action=delete">Delete</a></td>
                            </c:when>
                        </c:choose>
                        <td><a href="detail?id=${comment.postId}&replyId=${comment.id}&action=reply">Show Replies</a></td>

                        </tr>

                        <div id="reply">
                            <c:choose>
                                <c:when test='${fn:contains(sessionScope.replyList, comment.id)}'>
                                    <br>
                                    <c:forEach items="${sessionScope.currentComments}" var="reply">
                                        <c:choose>
                                            <c:when test = "${comment.id == reply.parentId}">
                                                <tr valign="top">
                                                    <td>${reply.author} - ${reply.postDate}${reply.state}:</td><br>
                                                <td>${reply.content}</td><br>
                                                <c:choose>
                                                    <c:when test ="${reply.username == sessionScope.user.username}">
                                                        <td><a href="detail?commentId=${reply.id}&action=edit1">Edit</a></td>
                                                        <td><a href="detail?id=${reply.id}&action=delete">Delete</a></td><br>
                                                    </c:when>
                                                    <c:when test="${sessionScope.role  == 'admin'}">
                                                        <td><a href="detail?id=${comment.id}&action=delete">Delete</a></td>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>

                        </div>
                        <c:choose>

                            <c:when test = "${replyId == comment.id}">

                                <h3>Reply:</h3><br>
                                <textarea name="content" cols="40" rows="5" required></textarea><br>
                                <input type ="hidden" name="parentId" value ="${comment.id}">
                                <input type="submit" name="action" value ="Reply"><br>
                            </c:when>
                        </c:choose>

                    </c:when>
                </c:choose>
            </c:forEach>
        </form>
    </div>

    <c:choose>
        <c:when test = "${function == 'edit1'}">
            <form action ="detail" method="post">
                <h1>Edit comment:</h1><br>
                <textarea name="content" cols="40" rows="5" required>${editComment.content}</textarea>
                <input type="hidden" name="id" value ="${editComment.id}">
                <input type="submit" name="action" value ="Edit">
                <input type="submit" name="action" value ="Cancel">
            </form>
        </c:when>
        <c:otherwise>
            <form action ="detail" method="post">
                <div id="ita">Leave a comment:</div><br>
                <textarea name="content" cols="120" rows="15" required></textarea><br>
                <input type="submit" name="action" value ="Add Comment">
            </form>
        </c:otherwise>
    </c:choose>

</form>



</body>
</html>


