package org.ee.jakarta.hotelmanagersystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
