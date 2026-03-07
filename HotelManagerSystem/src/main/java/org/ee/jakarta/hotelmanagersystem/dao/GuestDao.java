package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.Manager;
import org.ee.jakarta.hotelmanagersystem.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class GuestDao {
    private Connection conn;

    public GuestDao(Connection conn) {
        this.conn = conn;
    }

    public List<User> getAllGuests(){
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM guest ORDER BY id DESC";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User guest = new User();
                guest.setId(rs.getInt("id"));
                guest.setName(rs.getString("full_name"));
                guest.setEmail(rs.getString("email"));
                guest.setPhone(rs.getString("phone"));
                guest.setPassword(rs.getString("password"));
                guest.setStatus(rs.getString("status"));
                list.add(guest);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean addGuest(User guest){
        boolean flag = false;
        String sql = "INSERT INTO guest (full_name, email, password, phone) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.setString(3, guest.getPassword());
            ps.setString(4, guest.getPhone());

            int row = ps.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean updateGuest(User guest){
        boolean flag = false;
        String sql = "UPDATE guest SET full_name = ?, email = ?, phone = ?, status = ? WHERE id = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getEmail());
            ps.setString(3, guest.getPhone());
            ps.setString(4, guest.getStatus());
            ps.setInt(5, guest.getId());

            int row = ps.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public User getGuestById(int id){
        String sql = "SELECT * FROM guest WHERE id = ?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User guest = new User();
                guest.setId(rs.getInt("id"));
                guest.setName(rs.getString("full_name"));
                guest.setEmail(rs.getString("email"));
                guest.setPhone(rs.getString("phone"));
                guest.setStatus(rs.getString("status"));
                guest.setPassword(rs.getString("password"));
                return guest;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteGuest(int id){
        boolean flag = false;
        String sql = "DELETE FROM guest WHERE id = ?";

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
}
