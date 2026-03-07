<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="main">
    <div class="wrap">
        <ul class="menu">

            <c:if test="${empty userObj}">
                <li class="logo"><a href="index.jsp">Главная</a></li>
                <li><a href="admin_login.jsp">Администратор</a></li>
                <li><a href="guest_login.jsp">Гость</a></li>
            </c:if>
            <c:if test="${not empty userObj}">
                <li class="logo"><a href="../index.jsp">Главная</a></li>
                <li><a href="guest/booking.jsp">Бронирование</a></li>
                <li><a href="guest/my_bookings.jsp">Мои бронирования</a></li>
                <li><a href="userLogout">Выход</a></li>
            </c:if>
        </ul>
    </div>
</div>