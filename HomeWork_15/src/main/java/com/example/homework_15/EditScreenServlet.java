package com.example.homework_15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT * FROM users WHERE id = ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/userdb", "root", "123456")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body><div class='list'>");
            out.println("<h2>Редактирование пользователя</h2>");
            out.println("<form action='editUser' method='post' class='content'>");
            out.println("<input type='hidden' name='id' value='" + id + "'>");
            out.println("<table>");
            out.println("<tr><td>Имя пользователя</td><td><input type='text' name='username' value='" + rs.getString("username") + "'></td></tr>");
            out.println("<tr><td>Email</td><td><input type='email' name='email' value='" + rs.getString("email") + "'></td></tr>");
            out.println("<tr><td>Новый пароль (оставьте пустым, если не меняете)</td><td><input type='password' name='password'></td></tr>");
            out.println("<tr><td colspan='2'><input type='submit' value='Сохранить' class='submit'></td></tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("<a href='userList'>Назад к списку</a>");
            out.println("</div></body></html>");
        }catch (SQLException e){
            out.println("<h2>Ошибка: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
    }
}
