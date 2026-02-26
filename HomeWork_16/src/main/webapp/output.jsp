<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Информация о ноутбуке</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2>Выбранный ноутбук</h2>

    <dl class="spec">
        <dt>Производитель</dt>
        <dd><c:out value="${manufacturer}" default="—"/></dd>

        <dt>SSD</dt>
        <dd><c:out value="${ssd}" default="—"/></dd>

        <dt>Видеокарта</dt>
        <dd><c:out value="${videoCard}" default="—"/></dd>

        <dt>Процессор</dt>
        <dd><c:out value="${processor}" default="—"/></dd>

        <dt>Операционная система</dt>
        <dd><c:out value="${os}" default="—"/></dd>

        <dt>Тип матрицы</dt>
        <dd><c:out value="${matrixType}" default="—"/></dd>

        <dt>Разрешение экрана</dt>
        <dd><c:out value="${resolution}" default="—"/></dd>

        <dt>Сенсорный экран</dt>
        <dd><c:out value="${touchScreen}" default="Нет"/></dd>

        <dt>Подсветка клавиатуры</dt>
        <dd><c:out value="${keyBacklight}" default="Нет"/></dd>
    </dl>

    <a href="form.jsp" class="back-link">← Вернуться к выбору</a>
</div>
</body>
</html>
