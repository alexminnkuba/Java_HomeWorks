<%@ page import="org.ee.jakarta.hotelmanagersystem.db.DBConnect" %>
<%@ page import="java.util.List" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.GuestDao" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<section class="manager">
    <div class="wrap">
        <h3>Список гостей</h3>

        <div class="offcanvas offcanvas-start show" data-bs-backdrop="static" tabindex="-1" id="staticBackdrop"
             aria-labelledby="staticBackdropLabel">
            <div class="offcanvas-header">
                <h3 class="offcanvas-title" id="staticBackdropLabel">Редактировать гостя</h3>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div>
                    <%
                        int id = Integer.parseInt(request.getParameter("id"));
                        GuestDao gDao = new GuestDao(DBConnect.getConn());
                        User guest = gDao.getGuestById(id);
                    %>

                    <form action="../editGuest" method="post">
                        <div class="mb-3">
                            <label for="full" class="form-label">Полное имя</label>
                            <input type="text" name="fullName" id="full" class="form-control" required
                                   value="<%= guest.getName() %>">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control" required
                                   value="<%= guest.getEmail() %>">
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Телефон</label>
                            <input type="text" name="phone" id="phone" class="form-control" required
                                   value="<%= guest.getPhone() %>">
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Статус</label>
                            <select name="status" id="status" class="form-control" required>
                                <option value="">--Выбор статуса --</option>
                                <option value="ПРОЖИВАЕТ">ПРОЖИВАЕТ</option>
                                <option value="ЗАБРОНИРОВАЛ">ЗАБРОНИРОВАЛ</option>
                                <option value="СЪЕХАЛ">СЪЕХАЛ</option>
                                <option value="ОТМЕНЕНО">ОТМЕНЕНО</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" name="password" id="password" class="form-control" required
                                   value="<%= guest.getPassword() %>">
                        </div>
                        <input type="hidden" name="id" value="<%= guest.getId() %>">
                        <button class="btn btn-primary mt-3">Обновить</button>
                    </form>
                </div>
            </div>
        </div>

        <table class="table table-hover table-responsive w-100">
            <thead>
            <tr>
                <th scope="col" class="col-4">ФИО</th>
                <th scope="col" class="col-3">Email</th>
                <th scope="col" class="col-2">Телефон</th>
                <th scope="col" class="col-2">Статус</th>
            </tr>
            </thead>
            <tbody>
            <%
                GuestDao dao2 = new GuestDao(DBConnect.getConn());
                List<User> guests = dao2.getAllGuests();
                for (User g : guests) {
            %>
            <tr>
                <td class="col-4"><%= g.getName() %>
                </td>
                <td class="col-3"><%= g.getEmail() %>
                </td>
                <td class="col-2"><%= g.getPhone() %>
                </td>
                <td class="col-2">
                        <span class="badge
                            <%= "ПРОЖИВАЕТ".equals(g.getStatus()) ? "bg-success" :
                                "СЪЕХАЛ".equals(g.getStatus()) ? "bg-secondary" :
                                "ЗАБРОНИРОВАЛ".equals(g.getStatus()) ? "bg-primary" : "bg-warning" %>">
                            <%= g.getStatus() %>
                        </span>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
