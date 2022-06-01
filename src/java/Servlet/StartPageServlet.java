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
import trivials.ViewedPost;

/**
 *
 * @author Admin
 */
public class StartPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(-1);
        session.setAttribute("viewedPost", new ViewedPost());
//Chưa có user trong session thì hủy session (dùng sau khi đăng xuất)
        try {
            session.getAttribute("user");
        } catch (NullPointerException npe) {
            session.invalidate();
        }
        BlogDAO bd = new BlogDAO();
//Làm số trang
        int x = bd.count();
        int y = 0;
        if (x % 10 == 0) {
            y = (int) Math.ceil(x / 10);
        } else {
            y = (int) Math.ceil(x / 10) + 1;
        }
        List<Integer> range = IntStream.rangeClosed(1, y)
                .boxed().collect(Collectors.toList());
        session.setAttribute("pages", range);

//Mặc định role khi vào (chưa đăng nhập)
        if (session.getAttribute("role") == null) {
            session.setAttribute("role", "guest");
            System.out.println("GUEST");
        }

//Lấy ra các category để hiển thị, rồi tiếp tục lấy ra các blog thuộc page 1
        CategoryDAO cd = new CategoryDAO();
        session.setAttribute("categories", cd.getCategories());
        ArrayList<Blog> ab = bd.getBlogsPage(1); //Lấy blog theo page
        request.setAttribute("ab", ab);
        request.getRequestDispatcher("mainPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
