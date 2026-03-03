package org.ee.jakarta.hotelmanagersystem.dao;

import org.ee.jakarta.hotelmanagersystem.entity.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {
    private Connection conn;

    public ManagerDao(Connection conn) {
        this.conn = conn;
    }

    public boolean addManager(Manager manager){
        boolean flag = false;
        String sql = "INSERT INTO manager (full_name, email, password, phone) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, manager.getFullName());
            ps.setString(2, manager.getEmail());
            ps.setString(3, manager.getPassword());
            ps.setString(4, manager.getPhone());

            int row = ps.executeUpdate();

            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    public List<Manager> getAllManagers() {
        List<Manager> list = new ArrayList<>();
        String sql = "SELECT * FROM manager ORDER BY full_name";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manager manager = new Manager();
                manager.setId(rs.getInt("id"));
                manager.setFullName(rs.getString("full_name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhone(rs.getString("phone"));
                manager.setPassword(rs.getString("password"));
                list.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateManager(Manager manager){
        boolean flag = false;
        String sql = null;

        if(manager.getPassword() != null && !manager.getPassword().trim().isEmpty()){
            sql = "UPDATE manager SET full_name = ?, email = ?, password = ?, phone = ? WHERE id = ?";
        } else{
            sql = "UPDATE manager SET full_name = ?, email = ?, phone = ? WHERE id = ?";
        }

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, manager.getFullName());
            ps.setString(2, manager.getEmail());
            if(manager.getPassword() != null && !manager.getPassword().trim().isEmpty()){
                ps.setString(3, manager.getPassword());
                ps.setString(4, manager.getPhone());
                ps.setInt(5, manager.getId());
            } else{
                ps.setString(3, manager.getPhone());
                ps.setInt(4, manager.getId());
            }

            int row = ps.executeUpdate();
            if(row == 1){
                flag = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return flag;
    }

    public Manager getManagerById(int id) {
        String sql = "SELECT * FROM manager WHERE id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Manager manager = new Manager();
                manager.setId(rs.getInt("id"));
                manager.setFullName(rs.getString("full_name"));
                manager.setEmail(rs.getString("email"));
                manager.setPhone(rs.getString("phone"));
                manager.setPassword(rs.getString("password"));
                return manager;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteManager(int id){
        boolean flag = false;
        String sql = "DELETE FROM manager WHERE id = ?";

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
