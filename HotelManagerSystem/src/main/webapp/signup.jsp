<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Регистрация гостя — Отель "Комфорт"</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navbar.jsp"/>
<div class="height">
    <section class="form">
        <h2>Регистрация гостя</h2>
        <c:if test="${not empty errorMsg}">
            <p class="center text-danger fs-3">${errorMsg}</p>
            <c:remove var="errorMsg" scope="session"/>
        </c:if>
        <form action="userRegister" method="post">
            <div class="mb-3">
                <label for="fullName" class="form-label">ФИО</label>
                <input type="text" class="form-control" id="fullName" name="fullName"
                      placeholder="Фамилия Имя Отчество" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                      placeholder="Адрес электронной почты" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="password" class="form-control" id="password" name="password"
                      placeholder="Пароль" required>
            </div>
            <button type="submit" class="btn button">Зарегистрироваться</button>
        </form>
    </section>
</div>


<jsp:include page="component/footer.jsp"/>
</body>
</html>
