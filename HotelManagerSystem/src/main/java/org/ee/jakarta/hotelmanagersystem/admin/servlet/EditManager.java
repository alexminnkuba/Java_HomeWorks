package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.ManagerDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;
import org.ee.jakarta.hotelmanagersystem.entity.Manager;

import java.io.IOException;

@WebServlet("/editManager")
public class EditManager extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fullName = req.getParameter("fullName");
        String email    = req.getParameter("email");
        String password = req.getParameter("password"); // может быть пустым
        String phone    = req.getParameter("phone");

        Manager manager = new Manager();
        manager.setId(id);
        manager.setFullName(fullName);
        manager.setEmail(email);
        manager.setPhone(phone);
        manager.setPassword(password.trim().isEmpty() ? null : password);

        ManagerDao mDao = new ManagerDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if(mDao.updateManager(manager)){
            session.setAttribute("sucMsg", "Менеджер обновлён");
        } else {
            session.setAttribute("errorMsg", "Ошибка обновления");
        }

        resp.sendRedirect("../admin/manager.jsp");
    }
}
