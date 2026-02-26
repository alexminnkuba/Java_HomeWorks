
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выбор ноутбука</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2>Заполните информацию о ноутбуке</h2>
<form action="notebook" method="post">
    <label for="manufacturer">Название производителя:</label>
    <select name="manufacturer" id="manufacturer">
        <option value="Asus">ASUS</option>
        <option value="Lenovo">LENOVO</option>
        <option value="Hp">HP</option>
        <option value="Dell">DELL</option>
        <option value="Acer">ACER</option>
    </select>

    <label for="ssd">Объем SSD:</label>
    <select name="ssd" id="ssd">
        <option value="256 GB">256 GB</option>
        <option value="512 GB">512 GB</option>
        <option value="1 TB">1 TB</option>
        <option value="2 TB">2 TB</option>
    </select>

    <div class="radio-group">
    <label for="videoCard">Тип видеокарты</label>
    <input type="radio" name="videoCard" id="videoCard" value="Встроенная" checked>Встроенная
    <input type="radio" name="videoCard" id="videoCard" value="Дискретная">Дискретная
    </div>

    <label for="Cpu">Модель процессора:</label>
    <input type="text" id="Cpu" name="processor" placeholder="Например: Intel Core i7-13700H">

    <label for="Os">Операционная система:</label>
    <select name="os" id="Os">
        <option value="Windows 11">Windows 11</option>
        <option value="Windows 10">Windows 10</option>
        <option value="MacOS">MacOS</option>
        <option value="Linux">Linux</option>
    </select>

    <label for="matrixType">Тип матрицы:</label>
    <input type="radio" id="matrixType" name="matrixType" value="IPS" checked> IPS
    <input type="radio" id="matrixType" name="matrixType" value="TN"> TN
    <input type="radio" id="matrixType" name="matrixType" value="OLED"> OLED

    <label for="resolution">Разрешение экрана:</label>
    <select name="resolution" id="resolution">
        <option value="1920x1080 (Full HD)">1920x1080 (Full HD)</option>
        <option value="2560x1440 (2K)">2560x1440 (2K)</option>
        <option value="3840x2160 (4K)">3840x2160 (4K)</option>
    </select>

    <div class="checkbox-group">
    <label for="touchScreen">Сенсорный экран:</label>
    <input type="checkbox" id="touchScreen" name="touchScreen" value="Да"> Есть

    <label for="keyBacklight">Подсветка клавиатуры:</label>
    <input type="checkbox" id="keyBacklight" name="keyBacklight" value="Да"> Есть
    </div>

    <input type="submit" class="submit-btn" value="Показать информацию">
</form>
</div>
</body>
</html>
