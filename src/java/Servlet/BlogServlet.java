/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import dao.BlogDAO;
import dao.CategoryDAO;
import dao.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Comment;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditBlog", urlPatterns = {"/edit"})
public class BlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "";
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        BlogDAO bd = new BlogDAO();
        Blog b = new Blog();
        switch (action) {
            case "edit":
                b = bd.getBlog(Integer.parseInt(id));
                CategoryDAO cd = new CategoryDAO();
//            System.out.println(id);
                b.setCategories(cd.getBlogCategoryId(Integer.parseInt(id)));
                String content = b.getContent();
                content = content.replaceAll("<br />", "\r\n");
                b.setContent(content);
//            System.out.println(b.getCategories());
                request.setAttribute("b", b);
                url = "createNewPost.jsp";
                break;
            case "delete":
                b = bd.getBlog(Integer.parseInt(id));
                request.setAttribute("name", b.getAuthor());
                request.setAttribute("ct", b.getContent());
                request.setAttribute("id", b.getId());
                System.out.println(b.getContent());
                url = "deletePost.jsp";
                break;
            case "page":
                int page = Integer.parseInt(request.getParameter("page"));
                String name = (String) session.getAttribute("searchE");
                ArrayList<Blog> ab = bd.getBlogsByTitle(name, page);
                request.setAttribute("ab", ab);
                url = "mainPageSearch.jsp";
                break;
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "mainPage.jsp";
        String author = request.getParameter("author");

        String username = request.getParameter("username");
        String title = request.getParameter("title");
        String[] c = request.getParameterValues("categories");
        ArrayList<Integer> ac = new ArrayList<>();
        if (c != null) {
            for (int i = 0; i < c.length; i++) {
                ac.add(Integer.parseInt(c[i]));
            }
        }
        BlogDAO bd = new BlogDAO();
        String content = "";
        HttpSession session = request.getSession();
        Blog b = new Blog();
        switch (action) {
            case "Ok":
                content = request.getParameter("content");
                content = content.replaceAll("(\r\n|\n)", "<br />");
                b.setId(Integer.parseInt(request.getParameter("id")));
                b.setAuthor(author);
                b.setContent(content);
                b.setUsername(username);
                b.setTitle(title);
                b.setEditDate(LocalDate.now());
                b.setCategories(ac);
                bd.update(b);
                url = "/start";
                break;
            case "Post":
                content = request.getParameter("content");
                content = content.replaceAll("(\r\n|\n)", "<br />");
                b.setAuthor(author);
                b.setContent(content);
                b.setUsername(username);
                b.setTitle(title);
                b.setPostDate(LocalDate.now());
                b.setEditDate(LocalDate.now());
                b.setCategories(ac);
                bd.create(b); //Để là 0 cho đủ tham số khai báo, ko dùng id
                url = "/start";
                break;
            case "Cancel":
                url = "/start";
                break;
            case "Delete":
                String id = request.getParameter("id");
                b = bd.getBlog(Integer.parseInt(id));
                CommentDAO cd = new CommentDAO();
                ArrayList<Comment> comments = cd.getComments(b.getId());
                for (Comment e : comments) {
                    cd.delete(e);
                }
                bd.delete(b);
                url = "/start";
                break;
            case "Search":
                url = "mainPageSearch.jsp";
                System.out.println("Passsed asdasd");
                String name = request.getParameter("sTitle");
                ArrayList<Blog> ab = bd.getBlogsByTitle(name, 1);
//Làm số trang
                int x = bd.countWithSearch(name);
                int y = 0;
                if (x % 10 == 0) {
                    y = (int) Math.ceil(x / 10);
                } else {
                    y = (int) Math.ceil(x / 10) + 1;
                }
                List<Integer> range = IntStream.rangeClosed(1, y)
                        .boxed().collect(Collectors.toList());
                session.setAttribute("pages", range);
                session.setAttribute("searchE", name);
                request.setAttribute("ab", ab);
                break;
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

}
