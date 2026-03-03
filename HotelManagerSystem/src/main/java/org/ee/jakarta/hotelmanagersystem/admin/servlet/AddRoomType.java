package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.RoomTypeDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;
import org.ee.jakarta.hotelmanagersystem.entity.RoomType;

import java.io.IOException;


@WebServlet("/addRoomType")
public class AddRoomType extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeName = req.getParameter("typeName");

        RoomTypeDao dao = new RoomTypeDao(DBConnect.getConn());
        boolean result = dao.addRoomType(typeName);

        HttpSession session = req.getSession();

        if (result) {
            session.setAttribute("sucMsg", "Тип номера успешно добавлен");
            resp.sendRedirect("admin/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Ошибка при добавлении типа номера");
            resp.sendRedirect("admin/index.jsp");
        }
    }
}
