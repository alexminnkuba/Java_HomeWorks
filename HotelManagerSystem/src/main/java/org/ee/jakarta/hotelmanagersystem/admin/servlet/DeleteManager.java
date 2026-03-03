package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.ManagerDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;

import java.io.IOException;

@WebServlet("/deleteManager")
public class DeleteManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ManagerDao dao = new ManagerDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if(dao.deleteManager(id)){
            session.setAttribute("sucMsg", "Менеджер удалён");
        } else {
            session.setAttribute("errorMsg", "Не удалось удалить менеджера");
        }
        resp.sendRedirect("../admin/manager.jsp");
    }
}
