/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import dao.BlogDAO;
import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String url = "mainPageByCategory.jsp";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
//Fix nhanh lỗi null pointer
        if (action == null) {
            action = "";
        }
        if (action.equals("")) {
            BlogDAO bd = new BlogDAO();
            ArrayList<Blog> b = bd.getBlogsByCategory(Integer.parseInt(id), 1);
            request.setAttribute("ab", b);
            session.setAttribute("currentCategories", Integer.parseInt(id));
//Làm số trang
            int x = bd.countWithCategory(Integer.parseInt(id));
            int y = 0;
            if (x % 10 == 0) {
                y = (int) Math.ceil(x / 10);
            } else {
                y = (int) Math.ceil(x / 10) + 1;
            }

            List<Integer> range = IntStream.rangeClosed(1, y)
                    .boxed().collect(Collectors.toList());
            session.setAttribute("pages", range);
        }
        if (action.equals("page")) {
            BlogDAO bd = new BlogDAO();
            int page = Integer.parseInt(request.getParameter("page"));
            ArrayList<Blog> ab = bd.getBlogsByCategory((int) session.getAttribute("currentCategories"), page);
            request.setAttribute("ab", ab);
            url = "mainPageByCategory.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CategoryDAO cd = new CategoryDAO();
        String url = "";

        if (action.equals("Change")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String note = request.getParameter("note");
            cd.editCategory(id, name, note);
        }
        if (action.equals("Delete")) {

            String id = request.getParameter("id");
            cd.delete(Integer.parseInt(id));
        }
        if (action.equals("Add Category")) {
            String name = request.getParameter("name");
            String note = request.getParameter("note");
            if (cd.add(name, note) == false) {
                request.setAttribute("mess2", "Category name is already existed.<br>");
            }
        }
        ArrayList<Category> ac = new ArrayList<>();
        ac = cd.getCategories();
        request.setAttribute("ac", ac);
        request.setAttribute("function", "Add/Remove Categories");
        url = "controlPanel.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

}
