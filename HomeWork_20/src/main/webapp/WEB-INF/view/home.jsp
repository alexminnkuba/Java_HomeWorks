<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="form-container">
    <h1>Добавление книги</h1>

    <form action="addBook" method="post">
        <div class="form-group">
            <label for="title">Название книги</label>
            <input type="text" name="title" id="title" required placeholder="Например: Война и мир">
        </div>

        <div class="form-group">
            <label for="author">Автор</label>
            <input type="text" name="author" id="author" required placeholder="Лев Толстой">
        </div>

        <div class="form-group">
            <label for="year">Год издания</label>
            <input type="number" name="year" id="year" min="1900" max="2026" required placeholder="2020">
        </div>

        <button type="submit">Добавить книгу</button>
    </form>
</div>
</body>
</html>
