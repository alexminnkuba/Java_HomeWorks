package org.ee.jakarta.hotelmanagersystem.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ee.jakarta.hotelmanagersystem.dao.BookingDao;
import org.ee.jakarta.hotelmanagersystem.dao.RoomDao;
import org.ee.jakarta.hotelmanagersystem.db.DBConnect;
import org.ee.jakarta.hotelmanagersystem.entity.Booking;
import org.ee.jakarta.hotelmanagersystem.entity.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;


@WebServlet("/createBooking")
public class CreateBooking extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User guest = (User) session.getAttribute("userObj");

        if(guest == null){
            resp.sendRedirect("guest_login.jsp");
            return;
        }

       int roomId = Integer.parseInt(req.getParameter("roomId"));
        Date checkIn = Date.valueOf(req.getParameter("checkIn"));
        Date checkOut = Date.valueOf(req.getParameter("checkOut"));
        long nights = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);

        if(nights <= 0){
            session.setAttribute("errorMsg", "Дата выезда должна быть позже заезда");
            resp.sendRedirect("booking.jsp?roomId=" + roomId);
            return;
        }
        RoomDao roomDao = new RoomDao(DBConnect.getConn());
        BigDecimal pricePerNight = roomDao.getPricePerNight(roomId);

        if(pricePerNight == null){
            session.setAttribute("errorMsg", "Номер не найден или цена не указана");
            resp.sendRedirect("booking.jsp");
        }

        BigDecimal totalPrice = pricePerNight.multiply(BigDecimal.valueOf(nights));

        Booking booking = new Booking();
        booking.setGuestId(guest.getId());
        booking.setRoomId(roomId);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setTotalPrice(totalPrice);

        BookingDao bookingDao = new BookingDao(DBConnect.getConn());
        boolean f = bookingDao.createBooking(booking);

        if(f){
            session.setAttribute("sucMsg", "Номер успешно забронирован! Период: " + checkIn + " – " + checkOut + ", Сумма: " + totalPrice + " ₽");
        } else {
            session.setAttribute("errorMsg", "Номер занят на выбранные даты или произошла ошибка");
        }

        resp.sendRedirect("guest/booking.jsp");
    }
}
