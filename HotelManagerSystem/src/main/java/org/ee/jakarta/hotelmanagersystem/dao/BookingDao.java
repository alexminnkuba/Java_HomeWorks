package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private final Connection conn;

    public BookingDao(Connection conn) {
        this.conn = conn;
    }

    public boolean isRoomAvailable(int roomId, Date checkIn, Date checkOut){
        String sql = "SELECT COUNT(*) FROM booking WHERE room_id = ? AND status IN ('BOOKED', 'CHECKED_IN') AND (check_in < ? AND check_out > ?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, roomId);
            ps.setDate(2, checkIn);
            ps.setDate(3, checkOut);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean createBooking(Booking booking){
        boolean flag = false;

        if(!isRoomAvailable(booking.getRoomId(), booking.getCheckIn(), booking.getCheckOut())){
            flag = false;
        }

        String sql = "INSERT INTO booking (guest_id, room_id, check_in, check_out, total_price, status) VALUES (?, ?, ?, ?, ?, 'BOOKED')";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, booking.getGuestId());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, booking.getCheckIn());
            ps.setDate(4, booking.getCheckOut());
            ps.setBigDecimal(5, booking.getTotalPrice());
            int row = ps.executeUpdate();

            if(row ==1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public List<Booking> getBookingByGuest(int guestId){
        List<Booking> bookList = new ArrayList<>();
        String sql = "SELECT b.*, r.room_number, rt.type_name, g.full_name FROM booking b JOIN room r ON b.room_id = r.id JOIN room_type rt ON r.room_type_id = rt.id JOIN guest g ON b.guest_id = g.id WHERE b.guest_id = ? ORDER BY b.check_in DESC";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setGuestId(rs.getInt("guest_id"));
                b.setGuestName(rs.getString("full_name"));
                b.setRoomId(rs.getInt("room_id"));
                b.setRoomNumber(rs.getString("room_number"));
                b.setRoomTypeName(rs.getString("type_name"));
                b.setCheckIn(rs.getDate("check_in"));
                b.setCheckOut(rs.getDate("check_out"));
                b.setTotalPrice(rs.getBigDecimal("total_price"));
                b.setStatus(rs.getString("status"));
                b.setCreatedAt(rs.getTimestamp("created_at"));
                bookList.add(b);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return bookList;
    }

    public List<Booking> getBookingsByGuest(int guestId) {
        List<Booking> list = new ArrayList<>();
        String sql = """
        SELECT b.*, 
               rt.type_name AS room_type_name,
               g.full_name AS guest_name,
               r.room_number
        FROM booking b
        JOIN room_type rt ON b.room_id = rt.id
        JOIN guest g ON b.guest_id = g.id
        LEFT JOIN room r ON r.room_type_id = b.room_id
        WHERE b.guest_id = ?
        ORDER BY b.check_in DESC
    """;

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, guestId);
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setId(rs.getInt("id"));
                    b.setGuestId(rs.getInt("guest_id"));
                    b.setGuestName(rs.getString("guest_name"));
                    b.setRoomId(rs.getInt("room_id"));
                    b.setRoomTypeName(rs.getString("room_type_name"));
                    b.setCheckIn(rs.getDate("check_in"));
                    b.setCheckOut(rs.getDate("check_out"));
                    b.setStatus(rs.getString("status"));
                    // Если есть room_number — заполняем
                    b.setRoomNumber(rs.getString("room_number"));
                    list.add(b);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
