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


@WebServlet("/addRoom")
public class AddRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomNamber = req.getParameter("roomNamber");
        int roomTypeId = Integer.parseInt(req.getParameter("roomTypeId"));
        int floor = Integer.parseInt(req.getParameter("floor"));
        BigDecimal price = new BigDecimal(req.getParameter("pricePerNight"));
        String status = req.getParameter("status");
        String amenities = req.getParameter("amenities");
        String notes = req.getParameter("notes");

        Room room = new Room();
        room.setRoomNumber(roomNamber);
        room.setRoomTypeId(roomTypeId);
        room.setFloor(floor);
        room.setPricePerNight(price);
        room.setStatus(RoomStatus.valueOf(status));
        room.setAmenities(amenities);
        room.setNotes(notes);

        RoomDao rDao = new RoomDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        boolean f = rDao.addRoom(room);
        if(f){
            session.setAttribute("sucMsg", "Номер успешно добавлен");
        } else {
            session.setAttribute("errorMsg", "Ошибка сервера!");
        }

        resp.sendRedirect("admin/room.jsp");
    }
}
