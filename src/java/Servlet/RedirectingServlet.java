/*
Day la servlet de chuyen huong doi voi tat ca cac nut bam tren trang web
Cac nut bam dang co:
Edit profile
Log out

...
 */
package Servlet;

import dao.BlogDAO;
import dao.CategoryDAO;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Category;

@WebServlet(name = "RedirectingServlet", urlPatterns = {"/redirecting"})
public class RedirectingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        String url = "";
        if (action.equals("page")) {
            BlogDAO bd = new BlogDAO();
            int page = Integer.parseInt(request.getParameter("page"));
            ArrayList<Blog> ab = bd.getBlogsPage(page);
            request.setAttribute("ab", ab);
            url = "mainPage.jsp";
        }
        if (action.equals("deleteUser")) {
            LoginDAO ld = new LoginDAO();
            request.setAttribute("user", ld.getUser(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("delete", "User");
            url = "confirmDelete.jsp";
        }
        if (action.equals("editCategory") || action.equals("deleteCategory")) {
            request.setAttribute("action", action);
            CategoryDAO cd = new CategoryDAO();
            Category c = cd.getCategory(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("category", c);
            url = "editCategory.jsp";
        }
        if (action.equals("changeRole")) {
            LoginDAO ld = new LoginDAO();
            request.setAttribute("user", ld.getUser(Integer.parseInt(request.getParameter("id"))));
            ArrayList<String> roles = new ArrayList<>();
            roles.add("admin");
            roles.add("mob");
            request.setAttribute("roles", roles);
            url = "changeRole.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = null;
        String url = null;
        action = request.getParameter("action");
//Khi bam nut edit profile => chuyen sang editProfile.jsp
        if (action.equals("View Profile")) {
            url = "viewProfile.jsp";
        } else if (action.equals("Log In")) {
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("login", "login");
            url = "login.jsp";
        } else if (action.equals("Create a new post")) {
            request.setAttribute("create", "true");
            url = "createNewPost.jsp";
        } else if (action.equals("Log Out")) {
            HttpSession session = request.getSession();
            session.invalidate();
            url = "start";
        } else if (action.equals("Register")) {
            url = "login.jsp";
            request.setAttribute("login", "register");
        } else if (action.equals("Control Panel")) {
            url = "controlPanel.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

}
