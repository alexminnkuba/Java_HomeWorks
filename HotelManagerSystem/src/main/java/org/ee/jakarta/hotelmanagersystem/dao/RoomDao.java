package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.Room;
import org.ee.jakarta.hotelmanagersystem.entity.RoomStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private Connection conn;

    public RoomDao(Connection conn) {
        this.conn = conn;
    }

    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT r.*, rt.type_name FROM room r LEFT JOIN room_type rt ON r.room_type_id = rt.id ORDER BY CAST(r.room_number AS UNSIGNED), r.room_number";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery(sql)) {

            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setRoomNumber(rs.getString("room_number"));
                r.setRoomTypeId(rs.getInt("room_type_id"));
                r.setRoomTypeName(rs.getString("type_name"));
                r.setFloor(rs.getInt("floor"));
                r.setPricePerNight(rs.getBigDecimal("price_per_night"));
                r.setStatus(RoomStatus.fromString(rs.getString("status")));
                r.setAmenities(rs.getString("amenities"));
                r.setNotes(rs.getString("notes"));
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public boolean addRoom(Room room){
        boolean flag = false;
        String sql = "INSERT INTO room (room_number, room_type_id, floor, price_per_night, status, amenities, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getRoomTypeId());
            ps.setInt(3, room.getFloor());
            ps.setBigDecimal(4, room.getPricePerNight());
            ps.setString(5, room.getStatus().name());
            ps.setString(6, room.getAmenities());
            ps.setString(7, room.getNotes());

            int row = ps.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return flag;
    }

    public Room getRoomById(int id){
        String sql = "SELECT * FROM room WHERE id = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setRoomTypeId(rs.getInt("room_type_id"));
                room.setFloor(rs.getInt("floor"));
                room.setPricePerNight(rs.getBigDecimal("price_per_night"));
                room.setStatus(RoomStatus.valueOf(rs.getString("status")));
                room.setAmenities(rs.getString("amenities"));
                room.setNotes(rs.getString("notes"));

                return room;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateRoom(Room room){
        boolean flag = false;
        String sql = "UPDATE room SET room_number = ?, room_type_id = ?, floor = ?, price_per_night = ?, status = ?, amenities = ?, notes = ? WHERE id = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getRoomTypeId());
            ps.setInt(3, room.getFloor());
            ps.setBigDecimal(4, room.getPricePerNight());
            ps.setString(5, room.getStatus().name());
            ps.setString(6, room.getAmenities());
            ps.setString(7, room.getNotes());
            ps.setInt(8, room.getId());

            int row = ps.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return flag;
    }

    public boolean deleteRoom(int id){
        boolean flag = false;
        String sql = "DELETE FROM room WHERE id = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int row = ps.executeUpdate();

            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    public BigDecimal getPricePerNight(int roomId){
        String sql = "SELECT price_per_night FROM room WHERE id = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getBigDecimal("price_per_night");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
