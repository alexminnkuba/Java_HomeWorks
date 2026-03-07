package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.GuestDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;
import org.ee.jakarta.hotelmanagersystem.entity.User;

import java.io.IOException;


@WebServlet("/addGuest")
public class AddGuest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");
        String phone    = req.getParameter("phone");

        User guest = new User(fullName, email, password, phone);
        GuestDao gDao = new GuestDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        boolean f = gDao.addGuest(guest);

        if(f){
            session.setAttribute("sucMsg", "Гость успешно добавлен");
        } else{
            session.setAttribute("errorMsg", "Ошибка сервера!");
        }

        resp.sendRedirect("admin/guest.jsp");
    }
}
