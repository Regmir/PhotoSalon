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
<h3>Регистрация</h3>
<br/>
<form method="POST" action="<c:url value="/user/add"/>">
<table class="table information_json">
    <tr><th>Имя пользователя</th><td></td><td><input type="text" class="form-control" name="name" placeholder="Имя пользователя"></td></tr>
    <tr><th>Пароль</th><td></td><td><input type="password" class="form-control" name="pass" placeholder="Пароль"></td></tr>
    <tr><th>Электронная почта</th><td></td><td><input type="text" class="form-control" name="email" placeholder="Электронная почта"></td></tr>
    <tr><th>Секретное слово*</th><td></td><td><input type="password" class="form-control" name="secret" placeholder="Слово"></td></tr>
    <tr><th><input type="submit" class="form-control" value="Зарегистрироваться"></th></tr>
</table>
</form>
<br/>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>