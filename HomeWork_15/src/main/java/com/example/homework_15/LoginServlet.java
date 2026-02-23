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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String query = "SELECT * FROM users WHERE email = ?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String email = req.getParameter("email");
        String tempPassword = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/userdb", "root", "123456")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body><div class='list'>");
            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(tempPassword, storedHash)) {
                    resp.sendRedirect("userList");
                } else {
                    out.println("<h2 class='danger'>Неверное имя пользователя или пароль!</h2>");
                    out.println(" <div class=\"content\">");
                    out.println("<a class='nav-reg' href='login.html'>Авторизация</a>");
                    out.println("<a class='nav-reg' href='register.html'>Регистрация</a>");
                    out.println("<a href='home.html'>На главную</a>");
                    out.println("</div>");
                }
            }
        } catch (SQLException e) {
            out.println("<h2>Ошибка: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
        out.println("</div></body></html>");
    }
}
