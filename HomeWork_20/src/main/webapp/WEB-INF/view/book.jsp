<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="card">
    <c:if test="${not empty msg}">
        <div class="success">${msg}</div>
    </c:if>

    <h2>Информация о книге</h2>

    <ul>
        <li><strong>Название книги:</strong> ${book.title}</li>
        <li><strong>Автор:</strong> ${book.author}</li>
        <li><strong>Год издания:</strong> ${book.year}</li>
    </ul>
</div>
</body>
</html>
