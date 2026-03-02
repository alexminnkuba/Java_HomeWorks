<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Отель "Комфорт"</title>
    <jsp:include page="component/allcss.jsp"/>
</head>
<body>
<jsp:include page="component/navbar.jsp"/>
<div id="carouselExampleIndicators" class="carousel slide hero-carousel" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Слайд 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Слайд 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Слайд 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="img/hotel_1.jpg" class="d-block w-100 img-fluid" alt="Номер люкс">
            <div class="carousel-caption d-none d-md-block">
                <h3 class="caption-title">Номер люкс</h3>
                <p class="caption-text">Просторный номер с панорамным видом на город и всеми удобствами для комфортного отдыха</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="img/hotel_2.jpg" class="d-block w-100 img-fluid" alt="Бассейн">
            <div class="carousel-caption d-none d-md-block">
                <h3 class="caption-title">Крытый бассейн</h3>
                <p class="caption-text">Круглогодичный бассейн с подогревом и зоной отдыха</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="img/hotel_3.jpg" class="d-block w-100 img-fluid" alt="Ресторан">
            <div class="carousel-caption d-none d-md-block">
                <h3 class="caption-title">Ресторан</h3>
                <p class="caption-text">Изысканная кухня от нашего шеф‑повара</p>
            </div>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Предыдущий</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Следующий</span>
    </button>
</div>
<section class="advantages">
    <div class="wrap">
        <h2>Добро пожаловать в наш отель</h2>
        <div class="advantages__block">
            <div class="advantages__element-left">
                <div>
                    <h4>Комфортные номера</h4>
                    <p>От стандартных до люксов — все номера оснащены современной техникой</p>
                </div>
                <div>
                    <h4>Бесплатная парковка</h4>
                    <p>Для гостей отеля — охраняемая парковка на территории</p>
                </div>
                <div>
                    <h4>Завтрак включён</h4>
                    <p>Шведский стол с 7:00 до 10:00 каждый день</p>
                </div>
                <div>
                    <h4>Развлечения</h4>
                    <p>Развлекательные программы для наших гостей с утра и до позднего вечера</p>
                </div>
            </div>
            <div class="advantages__element-right">
                <img src="img/hotel_building.jpg" alt="Здание отеля"/>
            </div>
        </div>
    </div>
</section>


<jsp:include page="component/footer.jsp"/>
</body>
</html>