package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.RoomDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;

import java.io.IOException;

@WebServlet("/deleteRoom")
public class DeleteRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        RoomDao dao = new RoomDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        boolean f = dao.deleteRoom(id);
        if(f){
            session.setAttribute("sucMsg", "Номер успешно удалён");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера!");
        }
        resp.sendRedirect("admin/room.jsp");
    }
}
