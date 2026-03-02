package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.entity.User;

import java.io.IOException;


@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        if("admin@gmail.com".equals(email) && "admin".equals(password)){
            session.setAttribute("adminObj", new User());
            resp.sendRedirect("admin/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Неверный email или пароль!");
            resp.sendRedirect("admin_login.jsp");
        }
    }
}
