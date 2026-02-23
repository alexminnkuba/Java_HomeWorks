package com.example.homework_15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;



@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
private static final String query1 = "UPDATE users SET username = ?, email = ? WHERE id = ?";
private static final String query2 = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>> EditUserServlet doPost вызван, id = " + req.getParameter("id"));
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String newPassword = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/userdb", "root", "123456")){
            PreparedStatement ps;
            if(newPassword == null || newPassword.trim().isEmpty()){
                ps = conn.prepareStatement(query1);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setInt(3, id);
            }else{
                String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
                ps = conn.prepareStatement(query2);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, newHashedPassword);
                ps.setInt(4, id);
            }
            ps.executeUpdate();
            resp.sendRedirect("userList");
        }catch (SQLException e){
            out.println("<h2>Ошибка: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}
