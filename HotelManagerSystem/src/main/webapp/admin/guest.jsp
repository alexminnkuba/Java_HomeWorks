<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.ManagerDao" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.db.DBConnect" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.Manager" %>
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

        <c:if test="${not empty sucMsg}">
            <div class="alert alert-success text-center">${sucMsg}</div>
            <c:remove var="sucMsg" scope="session"/>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger">${errorMsg}</div>
            <c:remove var="errorMsg" scope="session"/>
        </c:if>

        <button class="btn btn-primary btn-info btn-doctor" type="button" data-bs-toggle="offcanvas"
                data-bs-target="#staticBackdrop" aria-controls="staticBackdrop">
            Добавить гостя
        </button>

        <div class="offcanvas offcanvas-start" data-bs-backdrop="static" tabindex="-1" id="staticBackdrop"
             aria-labelledby="staticBackdropLabel">
            <div class="offcanvas-header">
                <h3 class="offcanvas-title" id="staticBackdropLabel">Добавить гостя</h3>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div>
                    <form action="../addGuest" method="post">
                        <div class="mb-3">
                            <label for="full" class="form-label">Полное имя</label>
                            <input type="text" name="fullName" id="full" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Телефон</label>
                            <input type="text" name="phone" id="phone" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" name="password" id="password" class="form-control" required>
                            <button class="btn btn-primary mt-3">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <table class="table table-hover table-responsive w-100">
            <thead>
            <tr>
                <th scope="col" class="col-3">ФИО</th>
                <th scope="col" class="col-2">Email</th>
                <th scope="col" class="col-2">Телефон</th>
                <th scope="col" class="col-2">Статус</th>
                <th scope="col" class="col-2">Действия</th>
            </tr>
            </thead>
            <tbody>
            <%
                GuestDao gDao = new GuestDao(DBConnect.getConn());
                List<User> guests = gDao.getAllGuests();
                for(User guest : guests){
            %>
            <tr>
                <td class="col-3"><%= guest.getName()%></td>
                <td class="col-2"><%= guest.getEmail() %></td>
                <td class="col-2"><%= guest.getPhone() %></td>
                <td>
                        <span class="badge
                            <%= "ПРОЖИВАЕТ".equals(guest.getStatus()) ? "bg-success" :
                                "СЪЕХАЛ".equals(guest.getStatus()) ? "bg-secondary" :
                                "ЗАБРОНИРОВАЛ".equals(guest.getStatus()) ? "bg-primary" : "bg-warning" %>">
                            <%= guest.getStatus() %>
                        </span>
                </td>
                <td class="col-2">
                    <a href="edit_guest.jsp?id=<%= guest.getId() %>" class="btn btn-sm btn-info">Изменить</a>
                    <a href="../deleteGuest?id=<%= guest.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Удалить гостя?');">Удалить</a>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
