package com.example.homework_15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
    private static final String query = "SELECT * FROM users";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><head><link rel='stylesheet' href='css/style.css'></head><body>");
        out.println("<div class='list'><h2>Список пользователей</h2>");
        out.println("<table><tr><th>ID</th><th>Имя пользователя</th><th>Email</th><th>Редактировать</th><th>Удалить</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/userdb", "root", "123456")){
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td><a href='editScreen?id=" + id + "'>Редактировать</a></td>");

                out.println("<td><form action='deleteUser' method='post'>");
                out.println("<input type='hidden' name='id' value='"+ id + "'>");
                out.println("<button type='submit' class='delete-btn' onclick=\"return confirm('Вы уверены, что хотите удалить пользователя?')\">Удалить</button>");
                out.println("</form></td>");
            }
        }catch (SQLException e){
            out.println("<h2>Ошибка: " + e.getMessage() + "</h2>");
            e.printStackTrace();
        }
        out.println("</table>");
        out.println(" <div class=\"content\">");
        out.println("<a href='home.html'>На главную</a>");
        out.println("</div>");
        out.println("</div></body></html>");
    }
}
