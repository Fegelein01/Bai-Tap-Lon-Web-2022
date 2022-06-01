/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import dao.ChangePasswordDAO;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import trivials.Mailer;
import trivials.PasswordHashing;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ForgetPassword", urlPatterns = {"/forget"})
public class ForgetPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("page", "1");
        request.getRequestDispatcher("forget.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String page = request.getParameter("page");
        String mess1 = null;
        LoginDAO ld = new LoginDAO();
        String url = "forget.jsp";
        if (page == null) {
            page = "1";
        }
        if (page.equals("1")) {
            if (ld.checkExisted("email", email)) {
                int random_int = (int) Math.floor(Math.random() * (9999 - 1000 + 1) + 1000);
                HttpSession s = request.getSession();
                s.setMaxInactiveInterval(60 * 5);
                s.setAttribute("code", random_int);
                //Gửi mail
                Mailer m = new Mailer();
                m.send("viethungnumber2@gmail.com", "LoneHero123", email, "Confirmation Code", "Here is your confirmation code: " + random_int);
                request.setAttribute("page", "2");
                request.setAttribute("email", email);
            } else {
                mess1 = "Incorrect Email.<br>";
                request.setAttribute("page", "1");
                request.setAttribute("mess1", mess1);
            }
        }
        if (page.equals("2")) {
            String code = request.getParameter("code");
            if (code == null || code.equals("")) {
                code = "1"; //Trống hoặc null thì chắc chắn sai
            }
            HttpSession s = request.getSession();
            int cCode = (int) s.getAttribute("code");
            if (cCode == Integer.parseInt(code)) {
                request.setAttribute("page", "3");
                request.setAttribute("email", email);
            } else {
                mess1 = "Incorrect Confirmation Code.<br>";
                request.setAttribute("page", "2");
                request.setAttribute("mess1", mess1);
            }
        }
        if (page.equals("3")) {
            String p1 = request.getParameter("password1");
            String p2 = request.getParameter("password2");
            String mess2 = null;
            if (p1.equals("") || p1 == null) {
                mess2 = "Password can not be empty.<br>";
            } else {
                if (p1.equals(p2)) {
                    ChangePasswordDAO cpd = new ChangePasswordDAO();
                    PasswordHashing ph = new PasswordHashing();
                    cpd.changeViaEmail(email, ph.doHashing(p1));
                    url = "start";
                } else {
                    mess2 = "Confirmation password is not the same as the input password.<br>";
                }
            }
            request.setAttribute("email", email);
            request.setAttribute("mess2", mess2);
            request.setAttribute("page", "3");
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
