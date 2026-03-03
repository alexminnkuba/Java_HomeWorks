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


@WebServlet("/addManager")
public class AddManager extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");
        String phone    = req.getParameter("phone");

        Manager manager = new Manager(fullName, email, password, phone);
        ManagerDao mDao = new ManagerDao(DBConnect.getConn());

        HttpSession session = req.getSession();

        boolean f = mDao.addManager(manager);
        if(f){
            session.setAttribute("sucMsg", "Менеджер успешно добавлен");
        } else{
            session.setAttribute("errorMsg", "Ошибка сервера!");
        }

        resp.sendRedirect("admin/manager.jsp");
    }
}
