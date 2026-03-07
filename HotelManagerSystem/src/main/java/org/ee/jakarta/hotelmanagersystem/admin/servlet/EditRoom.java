package org.ee.jakarta.hotelmanagersystem.admin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.RoomDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;
import org.ee.jakarta.hotelmanagersystem.entity.Room;
import org.ee.jakarta.hotelmanagersystem.entity.RoomStatus;

import java.io.IOException;
import java.math.BigDecimal;


@WebServlet("/editRoom")
public class EditRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id               = Integer.parseInt(req.getParameter("id"));
        String roomNumber    = req.getParameter("roomNumber");
        int    roomTypeId    = Integer.parseInt(req.getParameter("roomTypeId"));
        int    floor         = Integer.parseInt(req.getParameter("floor"));
        BigDecimal price     = new BigDecimal(req.getParameter("pricePerNight"));
        String statusStr     = req.getParameter("status");
        String amenities     = req.getParameter("amenities");
        String notes         = req.getParameter("notes");

        Room room = new Room();
        room.setId(id);
        room.setRoomNumber(roomNumber);
        room.setRoomTypeId(roomTypeId);
        room.setFloor(floor);
        room.setPricePerNight(price);
        room.setStatus(RoomStatus.fromString(statusStr));
        room.setAmenities(amenities);
        room.setNotes(notes);

        RoomDao rDao = new RoomDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        boolean f = rDao.updateRoom(room);
        if(f){
            session.setAttribute("sucMsg", "Номер обновлён");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера!");
        }

        resp.sendRedirect("admin/room.jsp");
    }
}
