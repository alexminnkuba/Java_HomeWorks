package com.example.homework_15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String query = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String tempPassword = req.getParameter("password");

        String hashedPassword = BCrypt.hashpw(tempPassword, BCrypt.gensalt(12));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/userdb", "root", "123456")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            int row = ps.executeUpdate();

            out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body><div class='list'>");
            if(row == 1){
                out.println("<h2>Регистрация прошла успешно!</h2>");
                out.println(" <div class=\"content\">");
                out.println("<a href='login.html'>Авторизация</a>");
                out.println("</div>");
            }else{
                out.println("<h1>Ошибка регистрации</h1>");
            }
        }catch (SQLException e){
            out.println("<h1>Ошибка: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
        out.println("</div></body></html>");
    }
}
