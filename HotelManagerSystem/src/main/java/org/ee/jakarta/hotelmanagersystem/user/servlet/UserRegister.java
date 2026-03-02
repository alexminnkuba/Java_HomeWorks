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


@WebServlet("/userRegister")
public class UserRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(fullName, email, password);
        UserDao dao = new UserDao(DBConnect.getConn());

        HttpSession session = req.getSession();
        boolean flag = dao.register(user);

        if(flag){
            session.setAttribute("sucMsg", user.getName() + " успешно зарегистрирован(а)!");
            resp.sendRedirect("guest_login.jsp");
        } else{
            session.setAttribute("errorMsg", "Ошибка при регистрации");
            resp.sendRedirect("guest_login.jsp");
        }
    }
}
