/*
 Hiển thị nội dung bài viết kèm theo các commment
 */
package Servlet;

import dao.BlogDAO;
import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Comment;
import model.User;
import trivials.ViewedPost;

/**
 *
 * @author Admin
 */
public class BlogDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession s = request.getSession();
        CommentDAO cd = new CommentDAO();
        BlogDAO bd = new BlogDAO();
        String url = "";
//Khi bấm vào hiển thị comment cho post
        if (action.equals("edit1")) {
            String id = request.getParameter("commentId");
            Comment c = cd.getComment(Integer.parseInt(id));
            request.setAttribute("editComment", c);
            request.setAttribute("function", "edit1");
            ArrayList<Comment> ac = cd.getComments((int) s.getAttribute("postId"));
            s.setAttribute("currentComments", ac);
            url = "blog.jsp";
        }
        if (action.equals("delete")) {
            String id = request.getParameter("id");
            Comment c = cd.getComment(Integer.parseInt(id));
            cd.delete(c);
            ArrayList<Comment> ac = cd.getComments((int) s.getAttribute("postId"));
            s.setAttribute("currentComments", ac);
            url = "blog.jsp";
        }
        if (action.equals("reply")) {
            int id = Integer.parseInt(request.getParameter("replyId"));
            try {
                ArrayList<Integer> list = (ArrayList<Integer>) s.getAttribute("replyList");
                if (list.contains(id)) {
                    list.remove(list.indexOf(id));
                } else {
                    list.add(id);
                }
            } catch (NullPointerException npe) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(id);
                s.setAttribute("replyList", list);
            }
            action = "view";
        }
        if (action.equals("reply2")) {
            int id = Integer.parseInt(request.getParameter("replyId"));
            request.setAttribute("replyId", id);
            action = "view";
        }
        if (action.equals("view")) {
            int postId = -1;
            try {  
                postId = Integer.parseInt(request.getParameter("id"));
                s.setAttribute("PostId", postId);

            } catch (NullPointerException npe) { //Lúc này là các tình huống xem post không từ link ref
                postId = (int) s.getAttribute("PostId");
            }
            Blog b = bd.getBlog(postId);
            ViewedPost vp = (ViewedPost) s.getAttribute("viewedPost");

            if (vp.contain(postId) == false) { //Nếu chưa có trong danh sách đã xem
                bd.increaseView(postId);  //Tăng view
                vp.addPost(postId);
                s.setAttribute("viewedPost", vp);  //Cập nhật danh sách đã xem
            }
            s.setAttribute("postId", b.getId());
            s.setAttribute("currentBlog", b);
            ArrayList<Comment> ac = cd.getComments((int) s.getAttribute("postId"));
            s.setAttribute("currentComments", ac);
            if (ac.size() == 0) {
                request.setAttribute("mess1", "There aren't any comment yet. Be the first one to comment!<br>");
            }
            url = "blog.jsp";
        }
        if (action.equals("like")) {//Làm sau ( like với dislike)
            int postId = (int) s.getAttribute("postId");
            User user = (User) s.getAttribute("user");
            bd.dislikePost(postId, user.getUsername(), false); //Chỉ xem là người dùng đã dislike chưa, nếu rồi thì giảm và ko dislike nữa
            bd.likePost(postId, user.getUsername(), true); //Người dùng like post
            Blog b = bd.getBlog(postId);        //Lấy ra blog, rồi gán lại lên session
            s.setAttribute("currentBlog", b);
            url = "blog.jsp";
        }
        if (action.equals("dislike")) {
            int postId = (int) s.getAttribute("postId");
            User user = (User) s.getAttribute("user");
            bd.likePost(postId, user.getUsername(), false); //Tham số false tức là chỉ kiểm tra đã like chưa
            bd.dislikePost(postId, user.getUsername(), true); //Tham số true tức là kiểm tra xong và tăng nếu người dùng chưa dislike, giảm nếu người dùng đã
            Blog b = bd.getBlog(postId);
            s.setAttribute("currentBlog", b);
            url = "blog.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession s = request.getSession();
        String url = "";
        CommentDAO cd = new CommentDAO();
//ĐĂng comment, lấy các thông tin trên user(session) và postId(session)
        if (action.equals("Add Comment")) {
            User u = (User) s.getAttribute("user");
            int postId = (int) s.getAttribute("postId");
            String context = request.getParameter("content");
            context = context.replaceAll("(\r\n|\n)", "<br />"); //Chuyển /n sang <br> (xuống dòng trên jsp)
//Gắn vào đối tượng comment
            Comment c = new Comment();
            c.setAuthor(u.getName());
            c.setUsername(u.getUsername());
            c.setContent(context);
            c.setPostId(postId);
            c.setPostDate(LocalDate.now());
            c.setUsername(u.getUsername());
            c.setParentId(-1);
//Nhét vào database
            cd.addComment(c);
//Cách thủ công, lấy ra mảng xong chuyển sang jsp để hiển thị
            ArrayList<Comment> ac = cd.getComments(postId);
            s.setAttribute("currentComments", ac);
            url = "blog.jsp";
        }
        if (action.equals("Edit")) {
            int postId = (int) s.getAttribute("postId");
            int id = Integer.parseInt(request.getParameter("id"));
            String content = request.getParameter("content");
            cd.edit(id, content);
//Cách thủ công, lấy ra mảng xong chuyển sang jsp để hiển thị
            ArrayList<Comment> ac = cd.getComments(postId);
            s.setAttribute("currentComments", ac);
            url = "blog.jsp";
        }
        if (action.equals("Reply")) {
            System.out.println("Pass reply");
            User u = (User) s.getAttribute("user");
            int postId = (int) s.getAttribute("postId");
            int parentId = Integer.parseInt(request.getParameter("parentId"));
            String context = request.getParameter("content");
//Gắn vào đối tượng comment
            Comment c = new Comment();
            c.setAuthor(u.getName());
            c.setContent(context);
            c.setPostId(postId);
            c.setPostDate(LocalDate.now());
            c.setUsername(u.getUsername());
            c.setParentId(parentId);
//Nhét vào database
            cd.addComment(c);
//Cách thủ công, lấy ra mảng xong chuyển sang jsp để hiển thị
            ArrayList<Comment> ac = cd.getComments(postId);
            s.setAttribute("currentComments", ac);
            url = "blog.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
