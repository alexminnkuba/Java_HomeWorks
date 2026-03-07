<%@ page import="org.ee.jakarta.hotelmanagersystem.db.DBConnect" %>
<%@ page import="java.util.List" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.RoomDao" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.Room" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.RoomStatus" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.dao.RoomTypeDao" %>
<%@ page import="org.ee.jakarta.hotelmanagersystem.entity.RoomType" %>
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
        <h3>Все номера отеля</h3>
        <div class="offcanvas offcanvas-start show" data-bs-backdrop="static" tabindex="-1" id="staticBackdrop"
             aria-labelledby="staticBackdropLabel">
            <div class="offcanvas-header">
                <h3 class="offcanvas-title" id="staticBackdropLabel">Добавить номер</h3>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <div>
                    <%
                        int id = Integer.parseInt(request.getParameter("id"));
                        RoomDao dao = new RoomDao(DBConnect.getConn());
                        Room r = dao.getRoomById(id);
                    %>
                    <form action="../editRoom" method="post">
                        <div class="mb-3">
                            <label for="roomNumber" class="form-label">Номер комнаты</label>
                            <input type="text" name="roomNumber" id="roomNumber" class="form-control" required value="<%= r.getRoomNumber() %>">
                        </div>
                        <div class="mb-3">
                            <label for="typeNumber" class="form-label">Тип номера</label>
                            <select name="roomTypeId" class="form-select" required>
                                <option value="">Выберите тип</option>
                                <%
                                    RoomTypeDao typeDao = new RoomTypeDao(DBConnect.getConn());
                                    List<RoomType> types = typeDao.getAllRoomTypes();
                                    for (RoomType t : types) {
                                %>
                                <option value="<%= t.getId() %>"><%= t.getTypeName() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="floor" class="form-label">Этаж</label>
                            <input type="number" name="floor" id="floor" class="form-control" required value="<%= r.getFloor() %>">
                        </div>
                        <div class="mb-3">
                            <label for="pricePerNight" class="form-label">Цена за ночь</label>
                            <input type="number" name="pricePerNight" id="pricePerNight" class="form-control" required value="<%= r.getPricePerNight() %>">
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Статус</label>
                            <select name="status" class="form-select" id="status" required>
                                <option value=""><%= r.getStatus() %></option>
                                <option value="FREE">Свободен</option>
                                <option value="OCCUPIED">Занят</option>
                                <option value="CLEANING">Уборка</option>
                                <option value="MAINTENANCE">Ремонт / Обслуживание</option>
                                <option value="OUT_OF_ORDER">Неисправен / Выключен</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="amenities" class="form-label">Удобства (через запятую)</label>
                            <input type="text" name="amenities" id="amenities" class="form-control" value="<%= r.getAmenities() %>">
                        </div>
                        <div class="mb-3">
                            <label for="notes" class="form-label">Примечания</label>
                            <textarea name="notes" id="notes" class="form-control" rows="2"><%= r.getNotes() %></textarea>
                        </div>
                        <div class="mb-3">
                            <input type="hidden" name="id" value="<%= r.getId() %>">
                            <button class="btn btn-primary mt-3">Обновить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <table class="table table-hover table-responsive w-100">
            <thead>
            <tr>
                <th scope="col" class="col-1">№</th>
                <th scope="col" class="col-1">Номер</th>
                <th scope="col" class="col-1">Тип</th>
                <th scope="col" class="col-1">Этаж</th>
                <th scope="col" class="col-1">Цена/ночь</th>
                <th scope="col" class="col-2">Статус</th>
                <th scope="col" class="col-2">Удобства</th>
<%--                <th scope="col" class="col-2">Действия</th>--%>
            </tr>
            </thead>
            <tbody>
            <%
                RoomDao rDao = new RoomDao(DBConnect.getConn());
                List<Room> rooms = rDao.getAllRooms();
                int rowNum = 1;
                for(Room room : rooms){
            %>
            <tr>
                <td class="col-1"><%= rowNum++ %></td>
                <td class="col-1"><%= room.getRoomNumber() %></td>
                <td class="col-1"><%= room.getRoomTypeName()%></td>
                <td class="col-1"><%= room.getFloor() %></td>
                <td class="col-1"><%= room.getPricePerNight() %></td>
                <td class="col-2">
                    <span class="badge
                    <%= room.getStatus() == RoomStatus.FREE ? "bg-success" :
                        room.getStatus() == RoomStatus.OCCUPIED ? "bg-danger" :
                        room.getStatus() == RoomStatus.CLEANING ? "bg-warning" :
                        room.getStatus() == RoomStatus.MAINTENANCE ? "bg-info" : "bg-secondary" %> fs-6">
                    <%= room.getStatusDisplay() %>
                </span>
                </td>
                <td class="col-2">
                    <%= room.getAmenities() != null && !room.getAmenities().trim().isEmpty() ? room.getAmenities() : "—" %>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</section>
</body>
</html>
