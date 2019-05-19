<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-grid.min.css" rel="stylesheet" >
    <link href="${pageContext.request.contextPath}resources/css/bootstrap-reboot.css" rel="stylesheet">

</head>
<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow" style="border-bottom: 1px solid #eee;">
    <h5 class="my-0 mr-md-auto font-weight-normal">МОИФОТО</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#catalog">Каталог</a>
        <a class="p-2 text-dark" href="#catalog">Контакты</a>
    </nav>
    <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/">Выход</a>
</div>
<div class="container" id="catalog">
    <h3 class="m-3 pt-5" style="text-align: center">Каталог</h3>
    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 box-shadow">
            <div class="card-body">
                <h5 class="card-title">Список заказов</h5>
                <a href="<c:url value="/show/admin/order"/>">Редактировать</a>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-body">
                <h5 class="card-title">Список оборудования</h5>
                <a href="<c:url value="/show/admin/equipment"/>">Редактировать</a>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-body">
                <h5 class="card-title">Список салонов</h5>
                <a href="<c:url value="/show/admin/salon"/>">Редактировать</a>
                <a href="<c:url value="/createSalon"/>">Создать</a>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-body">
                <h5 class="card-title">Список типов оборудования</h5>
                <a href="<c:url value="/show/admin/equipmenttype"/>">Редактировать</a>
                <a href="<c:url value="/createEquipmenttype"/>">Создать</a>
            </div>
        </div>
        <div class="card mb-4 box-shadow">
            <div class="card-body">
                <h5 class="card-title">Список работников</h5>
                <a href="<c:url value="/show/admin/worker"/>">Редактировать</a>
                <a href="<c:url value="/createWorker"/>">Создать</a>
            </div>
        </div>
    </div>


    <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
            <div class="col-12 col-md">

            </div>
        </div>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
