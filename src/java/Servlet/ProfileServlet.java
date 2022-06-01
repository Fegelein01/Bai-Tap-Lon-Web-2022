/*
Xử lí sau khi bấm vào edit profile:
-Đổi được mật khẩu
-Đổi được tên
(email, thông tin cá nhân thì bổ sung sau)
*Giả sử cơ sở dữ liệu không có username nào trùng nhau hoặc email nào trùng nhau
 */
package Servlet;

import dao.ChangePasswordDAO;
import dao.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import trivials.PasswordHashing;

/**
 *
 * @author Admin
 */
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String nPassword1 = request.getParameter("nPassword1");
        String nPassword2 = request.getParameter("nPassword2");
        String action = request.getParameter("action");
        String url = "";
        String mess1 = "", mess2 = ""; //Validation hien ra man hinh
        if (action.equals("Change Password")) {
            boolean ok = true;
            if (password == null || password.equals("")) {
                ok = false;
                mess1 = "Password can not be empty.<br>";
            }
            if (nPassword1 == null || nPassword1.equals("")) {
                ok = false;
                mess2 = "Do you want to change your password or what?<br>";
            }
            if (nPassword1.equals(nPassword2) == false) {
                ok = false;
                mess2 = "New password need to be confirmed. (Confirm new password has to be the same as new password)<br>";
            }
            if (ok) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
// Object user lấy từ session có username, password, tên, role
                PasswordHashing ph = new PasswordHashing();
                password = ph.doHashing(password);
                if (user.getPassword().equals(password)) {
//ĐẾn lúc này thì mật khẩu trùng session, pass mới đã có + đúng confirm
//Đổi mật khẩu trong cơ sở dữ liệu, chỗ mà có username = username lấy từ session
                    ChangePasswordDAO cpd = new ChangePasswordDAO();
                    nPassword1 = ph.doHashing(nPassword1);
                    cpd.change(user.getUsername(), nPassword1);
//Cập nhật session
                    User u = new User();
                    u = (User) session.getAttribute("user");
                    u.setPassword(nPassword1);
                    session.setAttribute("user", u);
                    url = "/start";
                } else {
//Truong hop mat khau khong dung voi tai khoan (đang so sánh với user trên session)
                    url = "viewProfile.jsp";
                    request.setAttribute("mess1", "Incorrect password.<br>");
                }
            } else {
                request.setAttribute("mess1", mess1);
                request.setAttribute("mess2", mess2);
                url = "viewProfile.jsp";
            }
        }
        if (action.equals("Change Name")) {
            String name = request.getParameter("name");
            HttpSession s = request.getSession();
            User u = (User) s.getAttribute("user");
            if (u.getName().equals(name)) {
                url = "viewProfile.jsp";
                request.setAttribute("mess0", "New name has to be different!<br>");
            } else {
                request.setAttribute("name", name);
                url = "confirmChangeName.jsp";
            }

        }
        if (action.equals("Change Email")) {
            String email = request.getParameter("email");
            HttpSession s = request.getSession();
            User u = (User) s.getAttribute("user");
            LoginDAO ld = new LoginDAO();
            if (ld.checkExisted("email", email)) {
                url = "viewProfile.jsp";
                request.setAttribute("mess01", "Email is already existed!<br>");
            } else {
                request.setAttribute("email", email);
                url = "confirmChangeEmail.jsp";
            }

        }
        if (action.equals("Confirm")) {
            LoginDAO ld = new LoginDAO();
            HttpSession s = request.getSession();
            User u = (User) s.getAttribute("user");
            String name = request.getParameter("name");
            u.setName(name);
            s.setAttribute("user", u);
            ld.changeName(u.getId(), name);
            url = "viewProfile.jsp";
        }
        if (action.equals("Confirm ")) {
            LoginDAO ld = new LoginDAO();
            HttpSession s = request.getSession();
            User u = (User) s.getAttribute("user");
            String email = request.getParameter("email");
            u.setEmail(email);
            s.setAttribute("user", u);
            ld.changeEmail(u.getId(), email);
            url = "viewProfile.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

}
