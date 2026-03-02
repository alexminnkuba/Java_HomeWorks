package org.ee.jakarta.hotelmanagersystem.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.UserDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;
import org.ee.jakarta.hotelmanagersystem.entity.User;

import java.io.IOException;


@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        UserDao dao = new UserDao(DBConnect.getConn());
        User user = dao.login(email, password);

        if(user != null){
            session.setAttribute("userObj", user);
            resp.sendRedirect("index.jsp");
        } else{
            session.setAttribute("errorMsg", "Неверный логин или пароль!");
            resp.sendRedirect("guest_login.jsp");
        }
    }
}
