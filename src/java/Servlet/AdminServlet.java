/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import dao.BlogDAO;
import dao.CategoryDAO;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Blog;
import model.Category;
import model.User;

/**
 *
 * @author AdminServlet
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "";
        BlogDAO bd = new BlogDAO();
        ArrayList<Blog> ab = new ArrayList<>();
        switch (action) {
            case "title":
                ab = bd.getBlogsOrderBy("title");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
            case "author":
                ab = bd.getBlogsOrderBy("author");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
            case "post date":
                ab = bd.getBlogsOrderBy("post date");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
            case "last edit":
                ab = bd.getBlogsOrderBy("title");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
            case "view":
                ab = bd.getBlogsOrderBy("view");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
            case "like":
                ab = bd.getBlogsOrderBy("like");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
            case "dislike":
                ab = bd.getBlogsOrderBy("dislike");
                request.setAttribute("ab", ab);
                request.setAttribute("function", "Statistic");
                url = "controlPanel.jsp";
                break;
        }
        url = "controlPanel.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String actionDelete = request.getParameter("deleteAction");
        if (action == null) {
            action = "Users List";
        }
        if (actionDelete == null) { //Tránh nullpointer
            actionDelete = "";
        }
        String url = "";
        LoginDAO ld = new LoginDAO();
        if (actionDelete.equals("Yes")) { //Khi hành động là xóa user
            int id = Integer.parseInt(request.getParameter("userid"));
            ld.delete(id);
            ArrayList<User> au = ld.getUsers();
            request.setAttribute("au", au);
            request.setAttribute("function", "Users List");
            request.setAttribute("mess1", "Delete Completed!<br>");
            url = "controlPanel.jsp";
        } else if (actionDelete.equals("No")) {  //Nếu không phải hành động xóa user => đổi sang hành động hiển thị danh sách user
            action = "Users List";
        }
        if (action.equals("Ok")) {      //Khi bấm ok (chắc là từ trang confirmDelete
            int id = Integer.parseInt(request.getParameter("id"));
            String r = request.getParameter("role");
            ld.changeRole(id, r);
            action = "Users List";
        }
        if (action.equals("Users List")) {
            ArrayList<User> au = ld.getUsers();
            request.setAttribute("au", au);
            request.setAttribute("function", "Users List");
            url = "controlPanel.jsp";
        } else if (action.equals("Add/Remove Categories")) {  //Khi bấm nút trên trang controlPanel.jsp
            CategoryDAO cd = new CategoryDAO();
            ArrayList<Category> ac = new ArrayList<>();
            ac = cd.getCategories();
            request.setAttribute("ac", ac);
            request.setAttribute("function", "Add/Remove Categories");
            url = "controlPanel.jsp";
        } else if (action.equals("Statistic")) {
            BlogDAO bd = new BlogDAO();
            ArrayList<Blog> ab = bd.getBlogs();
            request.setAttribute("ab", ab);
            request.setAttribute("function", "Statistic");
            url = "controlPanel.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

}
