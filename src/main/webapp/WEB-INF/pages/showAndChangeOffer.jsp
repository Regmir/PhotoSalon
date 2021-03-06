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

<form method="POST" action="<c:url value="/offer/addOrEdit"/>">
    <table class="table information_json">
        <tr><th>Имя</th><td></td><td><input type="text" class="form-control" name="name" placeholder="Имя типа оборудования" value="${of.name}"></td></tr>
        <tr>
            <th>Возможности</th>
            <th></th>
        </tr>
        <c:forEach var="i" begin="0" end="${of.paramsCount-1}">
            <tr>
                <td></td>
                <td>
                    <c:if test="${of.paramsName[i]=='Время'}">
                    <select class="form-control" name="pname" ><option value="DESCRIPTION" >Ограничения</option>
                    <option value="OFFER_PRICE" >Цена</option><option selected="selected"  value="TIME_TO_OFFER">Время</option>
                    </select>
                    </c:if>
                    <c:if test="${of.paramsName[i]=='Ограничения'}">
                        <select class="form-control" name="pname" ><option value="DESCRIPTION" selected="selected">Ограничения</option>
                            <option value="OFFER_PRICE" >Цена</option><option  value="TIME_TO_OFFER">Время</option>
                        </select>
                    </c:if>
                    <c:if test="${of.paramsName[i]=='Цена'}">
                        <select class="form-control" name="pname" ><option value="DESCRIPTION" >Ограничения</option>
                            <option value="OFFER_PRICE" selected="selected">Цена</option><option   value="TIME_TO_OFFER">Время</option>
                        </select>
                    </c:if>
                </td>
                <td><input type="text" placeholder="Значение параметра" class="form-control" name="pval" value="${of.paramsVal[i]}" ></td>
                <td><span class="btn btn-danger minus pull-right">&ndash;</span></td>
            </tr>
        </c:forEach>
        <tr class="new_ability">
            <td></td>
            <td><span class="btn btn-success plus pull-right">+</span></td>
        </tr>
        <tr>
            <td><select class="form-control" name="flag" ><option selected="selected" value="new" >Сохранить как новый</option><option  value="old">Изменить существующий</option></select></td>
            <td><input type="hidden" name="id" class="form-control" id="id" value="${of.id}"></td>
        </tr>
        <tr><th><input type="submit" class="form-control" value="<spring:message text="Сохранить"/>"></th></tr>
    </table>
</form>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    // формируем новые поля
    jQuery('.plus').click(function(){
        jQuery('.new_ability').before(
            '<tr>' +
            '<td></td>'+
            '<td><select class="form-control" name="pname" >\n' +
            '                    <option value="DESCRIPTION" >Ограничения</option>\n' +
            '                    <option value="OFFER_PRICE" >Цена</option>\n' +
            '                    <option selected="selected"  value="TIME_TO_OFFER">Время</option>\n' +
            '                </select></td>'+
            '<td><input type="text" placeholder="Значение параметра" class="form-control" name="pval" ></td>'+
            '<td><span class="btn btn-danger minus pull-right">&ndash;</span></td>' +
            '</tr>'
        );
    });
    // on - так как элемент динамически создан и обычный обработчик с ним не работает
    jQuery(document).on('click', '.minus', function(){
        jQuery( this ).closest( 'tr' ).remove(); // удаление строки с полями
    });// JavaScript Document
</script>
</body>
</html>

