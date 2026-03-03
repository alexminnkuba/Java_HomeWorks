package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDao {
    private final Connection conn;

    public BookingDao(Connection conn) {
        this.conn = conn;
    }

    public boolean createBooking(Booking booking){
        boolean flag = false;
        String sql = "INSERT INTO booking (guest_id, room_type_id, check_in, check_out, status) VALUES (?, ?, ?, ?, 'BOOKED')";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, booking.getGuestId());
            ps.setInt(2, booking.getRoomTypeId());
            ps.setDate(3, booking.getCheckIn());
            ps.setDate(4, booking.getCheckOut());

            int row = ps.executeUpdate();

            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
}
