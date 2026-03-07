<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.User" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.Booking" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.db.DBConnect" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.BookingDao" %>

<html>
<head>
    <title>Мои бронирования — Отель Lux</title>
    <jsp:include page="../component/allcss.jsp"/>
    <style>
        .status-badge {
            font-size: 0.9rem;
            padding: 0.4em 0.8em;
        }
        .status-BOOKED    { background-color: #0d6efd; color: white; }
        .status-CHECKED_IN { background-color: #198754; color: white; }
        .status-CHECKED_OUT{ background-color: #6c757d; color: white; }
        .status-CANCELLED { background-color: #dc3545; color: white; }
    </style>
</head>
<body>
<jsp:include page="../component/navbar.jsp"/>

<c:if test="${empty userObj}">
    <c:redirect url="user_login.jsp"/>
</c:if>

<%
    User currentUser = (User) session.getAttribute("userObj");
    BookingDao bookingDao = new BookingDao(DBConnect.getConn());
    List<Booking> bookings = bookingDao.getBookingsByGuest(currentUser.getId());
    request.setAttribute("bookings", bookings);
%>

<div class="container mt-5">
    <h2 class="text-center mb-4">Мои бронирования</h2>

    <c:if test="${not empty sucMsg}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${sucMsg}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <c:remove var="sucMsg" scope="session"/>
    </c:if>

    <c:if test="${not empty errorMsg}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMsg}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <c:remove var="errorMsg" scope="session"/>
    </c:if>

    <c:choose>
        <c:when test="${empty bookings}">
            <div class="alert alert-info text-center py-4">
                У вас пока нет бронирований.<br>
                <a href="booking.jsp" class="btn btn-primary mt-3">Забронировать номер</a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-hover table-bordered align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>№</th>
                        <th>Тип номера</th>
                        <th>Заезд</th>
                        <th>Выезд</th>
                        <th>Статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="booking" items="${bookings}" varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <td>${booking.roomTypeName}</td>
                            <td>${booking.checkIn}</td>
                            <td>${booking.checkOut}</td>
                            <td>
                                <span class="badge status-badge status-${booking.status}">
                                        ${booking.status}
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="text-center mt-4">
        <a href="booking.jsp" class="btn btn-lg btn-primary">
            Забронировать ещё номер
        </a>
    </div>
</div>

</body>
</html>