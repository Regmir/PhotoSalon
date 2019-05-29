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

<h1>Заказы</h1>
<table class="table information_json">
    <tr>
        <th>Имя</th>
        <th>Тип</th>
        <th>Статус</th>
        <th>Салон</th>
        <th>Цена</th>
        <th>Изменить</th>
    </tr>
    <c:forEach items="${orders}" var="obj">
        <tr>
            <td>${obj.name}</td>
            <td>${obj.type}</td>
            <td>${obj.status}</td>
            <td>${obj.salonId}</td>
            <td>${obj.price}</td>
            <td><a href="objectsfromdbdata/${obj.id}" target="_blank">Изменить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
