<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Вход гостя — Отель "Комфорт"</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navbar.jsp"/>
<div class="height">
    <section class="form">
        <h2>Авторизация</h2>
        <c:if test="${not empty sucMsg}">
            <p class="center text-success fs-3">${sucMsg}</p>
            <c:remove var="sucMsg" scope="session"/>
        </c:if>
        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session"/>
        </c:if>
        <form action="userLogin" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn button">Войти</button>
        </form>
        <p class="center mt-3">Нет аккаунта? <a href="signup.jsp">Зарегистрироваться</a>
    </section>
</div>
<jsp:include page="component/footer.jsp"/>
</body>
</html>
