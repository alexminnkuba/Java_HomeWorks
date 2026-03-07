<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.RoomDao" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.db.DBConnect" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.BookingDao" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.RoomStatus" %>

<html>
<head>
    <title>Бронирование номера — Отель "Комфорт"</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="../component/navbar.jsp"/>

<c:if test="${empty userObj}">
    <c:redirect url="user_login.jsp"/>
</c:if>

<div class="container mt-5">
    <h2 class="text-center mb-4">Доступные номера</h2>

    <c:if test="${not empty sucMsg}">
        <div class="alert alert-success text-center">${sucMsg}</div>
        <c:remove var="sucMsg" scope="session"/>
    </c:if>
    <c:if test="${not empty errorMsg}">
        <div class="alert alert-danger text-center">${errorMsg}</div>
        <c:remove var="errorMsg" scope="session"/>
    </c:if>

    <form action="booking.jsp" method="get" class="mb-4">
        <div class="row g-3 justify-content-center">
            <div class="col-md-3">
                <label class="form-label">Заезд</label>
                <input type="date" name="checkIn" class="form-control" required value="${param.checkIn}">
            </div>
            <div class="col-md-3">
                <label class="form-label">Выезд</label>
                <input type="date" name="checkOut" class="form-control" required value="${param.checkOut}">
            </div>
            <div class="col-md-3 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Показать доступные номера</button>
            </div>
        </div>
    </form>

    <%
        String checkInStr = request.getParameter("checkIn");
        String checkOutStr = request.getParameter("checkOut");

        if (checkInStr != null && checkOutStr != null && !checkInStr.isEmpty() && !checkOutStr.isEmpty()) {
            java.sql.Date checkIn = java.sql.Date.valueOf(checkInStr);
            java.sql.Date checkOut = java.sql.Date.valueOf(checkOutStr);

            RoomDao roomDao = new RoomDao(DBConnect.getConn());
            List<Room> availableRooms = new ArrayList<>();

            for (Room r : roomDao.getAllRooms()) {
                if (r.getStatus() == RoomStatus.FREE ||
                        new BookingDao(DBConnect.getConn()).isRoomAvailable(r.getId(), checkIn, checkOut)) {
                    availableRooms.add(r);
                }
            }
            request.setAttribute("availableRooms", availableRooms);
            request.setAttribute("checkIn", checkInStr);
            request.setAttribute("checkOut", checkOutStr);
        }
    %>

    <c:if test="${not empty availableRooms}">
        <div class="row">
            <c:forEach var="room" items="${availableRooms}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${room.roomNumber} — ${room.roomTypeName}</h5>
                            <p class="card-text">
                                Этаж: ${room.floor}<br>
                                Цена за ночь: ${room.pricePerNight} ₽<br>
                                Статус: <strong>${room.statusDisplay}</strong><br>
                                Удобства: ${room.amenities != null && !room.amenities.isEmpty() ? room.amenities : '—'}
                            </p>
                            <form action="../createBooking" method="post">
                                <input type="hidden" name="roomId" value="${room.id}">
                                <input type="hidden" name="checkIn" value="${checkIn}">
                                <input type="hidden" name="checkOut" value="${checkOut}">
                                <button type="submit" class="btn btn-success w-100">
                                    Забронировать
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty availableRooms && not empty param.checkIn}">
        <div class="alert alert-info text-center mt-4">
            На выбранные даты свободных номеров нет. Попробуйте другие даты.
        </div>
    </c:if>
</div>

<%--<jsp:include page="../component/footer.jsp"/>--%>
</body>
</html>