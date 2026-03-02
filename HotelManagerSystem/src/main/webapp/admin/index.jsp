<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Админ-панель — Отель Lux</title>
    <jsp:include page="../component/allcss.jsp"/>
</head>
<body>
<jsp:include page="../admin/navbar.jsp"/>

<c:if test="${empty adminObj}">
    <c:redirect url="../admin_login.jsp"/>
</c:if>

<section class="admin">
    <div class="wrap">
        <h2>Панель администратора</h2>

        <div class="admin__block">
            <div class="admin__element">
                <img src="../img/admin_1.jpg" alt="">
                <h3>Менеджеры</h3>
                <p>8</p>
            </div>
            <div class="admin__element">
                <img src="../img/admin_2.jpg" alt="">
                <h3>Гости</h3>
                <p>142</p>
            </div>
            <div class="admin__element">
                <img src="../img/admin_3.jpg" alt="">
                <h3>Номера</h3>
                <p>67</p>
            </div>
            <div class="admin__element" data-bs-toggle="modal" data-bs-target="#exampleModal">
                <img src="../img/admin_4.jpg" alt="">
                <h3>Типы номеров</h3>
                <p>12</p>
            </div>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="addRoomType" method="post">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Добавить тип номера</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="typeName" class="form-label">Название типа номера</label>
                                <input type="text" class="form-control" id="typeName" name="typeName" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../component/footer.jsp"/>
</body>
</html>