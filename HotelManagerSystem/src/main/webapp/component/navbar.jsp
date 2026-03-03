<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="main">
    <div class="wrap">
        <ul class="menu">
            <li class="logo"><a href="index.jsp">Главная</a></li>
            <c:if test="${empty userObj}">
                <li><a href="admin_login.jsp">Администратор</a></li>
                <li><a href="guest_login.jsp">Гость</a></li>
            </c:if>
            <c:if test="${not empty userObj}">
                <li><a href="#">Бронирование</a></li>
                <li><a href="#">Мои бронирования</a></li>
                <li><a href="userLogout">Выход</a></li>
            </c:if>
        </ul>
    </div>
</div>