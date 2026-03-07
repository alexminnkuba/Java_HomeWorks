package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public boolean register(User user){
        boolean flag = false;
        String sql = "INSERT INTO guest (full_name, email, password, phone) VALUES (?, ?, ?, ?)";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());

            int row = ps.executeUpdate();

            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public User login(String email, String password){
        User user = null;
        String sql = "SELECT * FROM guest WHERE email = ? AND password = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }
}
