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


@WebServlet("/editGuest")
public class EditGuest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String status = req.getParameter("status");

        User guest = new User();
        guest.setId(id);
        guest.setName(name);
        guest.setEmail(email);
        guest.setPassword(password);
        guest.setPhone(phone);
        guest.setStatus(status);

        GuestDao gDao = new GuestDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        boolean f = gDao.updateGuest(guest);
        if(f){
            session.setAttribute("sucMsg", "Данные гостя обновлены");
        } else {
            session.setAttribute("errorMsg", "Ошибка обновления");
        }
        resp.sendRedirect("admin/guest.jsp");
    }
}
