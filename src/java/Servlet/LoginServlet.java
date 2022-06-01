/*
 Xet xem tai khoan nguoi dung co trong co so du lieu khong
neu co thi se phan quyen, gan vao session
-admin
-mob
sau do chuyen sang mainPage.jsp
 */
package Servlet;

import dao.BlogDAO;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.User;
import trivials.PasswordHashing;


/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("login", "login");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PasswordHashing ph = new PasswordHashing();
        password = ph.doHashing(password);
        
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String action = request.getParameter("action");
        LoginDAO ld = new LoginDAO();
//        Validation v = new Validation();
//        v.setValidations((ArrayList<String>) session.getAttribute("validation"));
//        System.out.println(v);
        String mess1 = null, mess2 = null, mess3 = null, mess4 = null, mess5 = null;
        String url = "";
        if (username.isEmpty() || username == null || password.isEmpty() || password == null) {
            if (username == null || username.isEmpty()) {
//                v.setValidation(0, "Username can't be empty.<br>");
                mess1 = "Username can't be empty.<br>";
            }
            if (password == null || password.isEmpty()) {
//                v.setValidation(1, "Password can't be empty.<br>");
                mess2 = "Password can't be empty.<br>";
            }
            request.setAttribute("username", username);
            request.setAttribute("mess1", mess1);
            request.setAttribute("mess2", mess2);
            request.setAttribute("login", login);
            url = "login.jsp";
        } else if (action.equals("Login")) {
            User user = new User(username, password);
            if (ld.checkLogin(user)) {
                user = ld.getUserByUsername(username);
                session.setAttribute("user", user);
                switch (user.getRole()) {
                    case "admin":
                        session.setAttribute("role", "admin");
                        break;
                    case "mob":
                        session.setAttribute("role", "mob");
                        break;
                    default:
                        session.setAttribute("role", "denied");
                        break;
                }
                
                url = "start";
            } else {
                url = "login.jsp";
                request.setAttribute("username", username);
                request.setAttribute("login", login);
                request.setAttribute("mess1", "Incorrect username/password.<br>");
            }
        } else if (action.equals("Register")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password2 = request.getParameter("password2");
            password2 = ph.doHashing(password2);
            request.setAttribute("login", login);
            request.setAttribute("username", username);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            int ok = 1;
            if (name == null || name.equals("")) {
                mess3 = "Name can't be empty.<br>";
                ok = 0;
            }
            if (email == null || email.equals("")) {
                mess5 = "Email can't be empty.<br>";
                ok = 0;
            }
            if (password2.equals(password) == false) {
                mess4 = "Incorrect confirmation password.<br>";
                ok = 0;
            }
            if (ok == 1) {
                User user = new User();
                user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setRole("mob");
                user.setUsername(username);
                user.setEmail(email);
                if (ld.register(user)) {
                    url = "completeLogin.jsp";
                } else {
                    if (ld.checkExisted("username", username)) {
                        mess1 = "Username is already existed.<br>";
                    }
                    if (ld.checkExisted("email", email)) {
                        mess5 = "Email is already existed.<br>";
                    }
                    request.setAttribute("mess1", mess1);
                    request.setAttribute("mess5", mess5);
                    url = "login.jsp";
                }
            } else {
                url = "login.jsp";
                request.setAttribute("mess3", mess3);
                request.setAttribute("mess4", mess4);
                request.setAttribute("mess5", mess5);
            }
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }
    
}
