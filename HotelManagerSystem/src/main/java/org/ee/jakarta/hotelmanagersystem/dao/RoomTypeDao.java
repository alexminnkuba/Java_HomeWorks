package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao {
    private Connection conn;

    public RoomTypeDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addRoomType(String roomType){
        boolean flag = false;
        String sql = "INSERT INTO room_type (type_name) VALUES (?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,roomType);
            int row = ps.executeUpdate();

            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return  flag;
    }

    public List<RoomType> getAllRoomTypes(){
        List<RoomType> roomTypes = new ArrayList<>();
        String sql = "SELECT * FROM room_type ORDER BY type_name";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                RoomType type = new RoomType();
                type.setId(rs.getInt("id"));
                type.setTypeName(rs.getString("type_name"));
                roomTypes.add(type);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return roomTypes;
    }
}
