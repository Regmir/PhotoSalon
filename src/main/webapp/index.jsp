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
  <link href="resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="resources/css/bootstrap-grid.min.css" rel="stylesheet" >
  <link href="resources/css/bootstrap-reboot.css" rel="stylesheet">

  <script type="text/javascript" src="https://gc.kis.v2.scr.kaspersky-labs.com/08CBBAD7-4CD1-9147-AEAE-65A3F22E52D4/main.js" charset="UTF-8"></script></head>
<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow" style="border-bottom: 1px solid #eee;">
  <h5 class="my-0 mr-md-auto font-weight-normal">МОИФОТО</h5>
  <nav class="my-2 my-md-0 mr-md-3">
    <a class="p-2 text-dark" href="#catalog">Каталог</a>
    <a class="p-2 text-dark" href="#catalog">Контакты</a>
  </nav>
  <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/register">Регистрация</a>
  <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/login">Вход</a>
</div>




<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center" style="border-bottom: 1px solid #eee;">
  <p class="lead">Сеть фотоцентров оперативной печати фотографий и документов.<br/>
    Фотосъемка на документы. Изготовление фотокниг и фотосувениров.</p>

  <div class="row">
    <div class="col-sm-4 col-md-3 col-lg-4 icon-wrapper">
      <div class="icon">
        <svg xmlns="http://www.w3.org/2000/svg" width="160" viewBox="0 0 160 130">
          <path fill="#E95352" d="M10.1 16.6l1 1.3 1.1-.9c.1-.1.4-.3.7-.7V24H15v-9.9h-1.7l-3.2 2.5z"></path>
          <path fill="#D3D3D3" d="M13.6 6C6.1 6 0 12.1 0 19.6 0 27 6.1 33.1 13.6 33.1S27.2 27 27.2 19.5C27.1 12.1 21.1 6 13.6 6zm0 25.9c-6.8 0-12.3-5.5-12.3-12.3 0-6.8 5.5-12.3 12.3-12.3 6.8 0 12.3 5.5 12.3 12.3 0 6.8-5.5 12.3-12.3 12.3zM72 92.1h52c3.2 0 5.8-2.6 5.8-5.8V18.4c0-3.2-2.6-5.8-5.8-5.8H43c-3.2 0-5.8 2.6-5.8 5.8v68c0 3.2 2.6 5.8 5.8 5.8h29zM43.2 67.6v-49h80.6v67.6H43.2V67.6z"></path>
          <path fill="#E95352" d="M88.9 45.6c4.9 0 9-4 9-8.9s-4-8.9-9-8.9c-4.9 0-9 4-9 8.9.1 4.8 4.1 8.9 9 8.9zm0-13c2.2 0 4 1.8 4 4s-1.8 4-4 4-4-1.8-4-4 1.8-4 4-4z"></path>
          <path fill="#D3D3D3" d="M96.1 57.1L90 63.2l.1.1 4.1 4.1 3.9-3.9L112.6 78c1.1 1.1 2.9 1.1 4 0l.2-.2c1.1-1.1 1.1-2.9 0-4l-16.7-16.7c-.5-.5-1.2-.7-2-.7-.7-.1-1.4.2-2 .7zM83.9 69.2l4.2 4.2 4.5 4.5c1.1 1.1 2.9 1.1 4 0l.2-.2c1.1-1.1 1.1-2.9 0-4l-4.5-4.5-4.1-4.2-12.7-12.6c-.1-.1-.2-.3-.4-.4-.5-.4-1.1-.6-1.7-.6-.6 0-1.2.2-1.7.6l-.2.2-.2.2-21.5 21.4c-1.1 1.1-1.1 2.9 0 4l.2.2c1.1 1.1 2.9 1.1 4 0l19.4-19.3 10.5 10.5z"></path>
          <path fill="#D3D3D3" d="M156.7 51.5l-16.9-7.9-7-3.3v6.6l4.4 2.1 16.7 7.9-6.1 13-4.7 10.1-7 14.8-3 6.3-7.9 16.8-16.7-7.9-9.3-4.4-20.9-9.8-1.2-.6-.1-.1H63l11.6 5.5 1.2.6 20.9 9.8 9.3 4.4 16.9 7.9c2.9 1.4 6.4.1 7.7-2.8l8-17 3-6.3 7-14.8 4.8-10.2 6.2-13.1c1.2-2.8 0-6.2-2.9-7.6z"></path>
        </svg>
      </div>
      <strong>Выберите продукцию</strong>
    </div>
    <div class="col-sm-4 col-md-6 col-lg-4 icon-wrapper">
      <div class="icon">
        <svg xmlns="http://www.w3.org/2000/svg" width="160" viewBox="0 0 160 130">
          <path fill="#E95352" d="M14.7 29.1l1.3-1.2c.9-.8 1.5-1.4 1.8-1.8.3-.4.6-.8.8-1.2.2-.4.2-.8.2-1.3s-.1-.9-.4-1.3c-.3-.4-.6-.7-1.1-1-.5-.2-1.1-.3-1.8-.3-.8 0-1.5.2-2.1.5-.3.2-.7.5-1.2.9l1.1 1.4c.8-.7 1.6-1 2.3-1 .4 0 .7.1.9.3.2.2.3.5.3 1s-.2 1-.6 1.5c-.2.3-.7.8-1.5 1.6L12 29.5v1.4h7v-1.8h-4.3z"></path>
          <path fill="#D3D3D3" d="M15.6 13C8.1 13 2 19.1 2 26.5 2 34 8.1 40.1 15.6 40.1S29.2 34 29.2 26.6c0-7.5-6.1-13.6-13.6-13.6zm0 25.9c-6.8 0-12.4-5.5-12.4-12.3 0-6.8 5.6-12.3 12.4-12.3 6.8 0 12.4 5.5 12.4 12.3 0 6.7-5.6 12.3-12.4 12.3zm59.6 54.2H46.3V25.6h80.8v40.8h6v-41c0-3.2-2.6-5.8-5.8-5.8H46.1c-3.2 0-5.8 2.6-5.8 5.8v67.8c0 3.2 2.6 5.8 5.8 5.8h54.2v-6H75.2z"></path>
          <path fill="#D3D3D3" d="M98.3 65.4v15.2l5.6-5.6L117 88c1.1 1.1 2.9 1.1 4 0l.2-.2c1.1-1.1 1.1-2.9 0-4l-13.1-13 5.4-5.4H98.3z"></path>
          <path fill="#D3D3D3" d="M152.2 72.3h-36.5l6 6H152v32.8h-39.9V88.7l-6-6v28.5c0 3.2 2.6 5.8 5.8 5.8h40.2c3.2 0 5.8-2.6 5.8-5.8V78.1c.1-3.2-2.5-5.8-5.7-5.8z"></path>
          <path fill="#E95352" d="M67.3 34.6h-12v11.9h6v-6h6m-12 10v11.9h12v-5.9h-6v-6m22-15.9h-12v5.9h6v6h6m-6 10h-6v5.9h12V50.5h-6"></path>
        </svg>

      </div>
      <strong>Загрузите фотографию</strong>
    </div>
    <div class="col-sm-4 col-md-3 col-lg-4 icon-wrapper">
      <div class="icon">
        <svg xmlns="http://www.w3.org/2000/svg" width="160" viewBox="0 0 160 130">
          <path fill="#E95352" d="M31.2 27.8c.7-.5 1-1.3 1-2.3 0-.7-.2-1.2-.6-1.6-.4-.4-1-.6-1.8-.7.7-.2 1.2-.5 1.6-.9.4-.4.6-1 .6-1.7s-.3-1.2-.9-1.7c-.6-.4-1.4-.6-2.5-.6-1.3 0-2.4.3-3.3 1l1 1.5c.8-.5 1.5-.8 2.1-.8 1 0 1.4.4 1.4 1.2 0 .5-.2.8-.5 1-.4.2-.9.3-1.7.3h-.8v1.7h.8c.8 0 1.5.1 1.8.3.4.2.6.5.6 1s-.2.9-.5 1.1c-.3.2-.9.4-1.7.4-.8 0-1.6-.2-2.5-.7V28c.8.4 1.9.5 3.1.5 1.2.1 2.1-.2 2.8-.7z"></path>
          <path fill="#D3D3D3" d="M28.7 37.6c7.6 0 13.7-6.1 13.7-13.7s-6.2-13.7-13.7-13.7c-7.6 0-13.7 6.1-13.7 13.7s6.2 13.7 13.7 13.7zm0-26.1c6.9 0 12.5 5.6 12.5 12.5s-5.6 12.5-12.5 12.5S16.2 30.9 16.2 24s5.6-12.5 12.5-12.5z"></path>
          <path fill="#E95352" d="M86.5 33h45.6c1.6 0 2.9-1.3 2.9-2.8v-.3c0-1.6-1.3-2.8-2.9-2.8H86.5c-1.6 0-2.9 1.3-2.9 2.8v.3c.1 1.5 1.3 2.8 2.9 2.8zm0 10h45.6c1.6 0 2.9-1.3 2.9-2.8v-.3c0-1.6-1.3-2.8-2.9-2.8H86.5c-1.6 0-2.9 1.3-2.9 2.8v.3c.1 1.6 1.3 2.8 2.9 2.8zm0 11.1h45.6c1.6 0 2.9-1.3 2.9-2.8V51c0-1.6-1.3-2.8-2.9-2.8H86.5c-1.6 0-2.9 1.3-2.9 2.8v.3c.1 1.5 1.3 2.8 2.9 2.8z"></path>
          <path fill="#D3D3D3" d="M29.3 90.4c-3.1-3.1-8.1-3.1-11.2 0-3.1 3.1-3.1 8.1 0 11.2l22.6 22.6c3.1 3.1 8.1 3.1 11.2 0 3.1-3.1 3.1-8.1 0-11.2L29.3 90.4zm19 30.2c-1.1 1.1-3 1.1-4.1 0L21.6 98.1c-1.1-1.1-1.1-3 0-4.1 1.1-1.1 3-1.1 4.1 0l22.6 22.6c1.1 1.1 1.1 2.9 0 4zm95.3-49.5c-1.8-2.4-4.9-3.5-7.7-2.9-3.9.5-10 4.5-18.8 10.6-4.2 2.9-9.9 6.9-11.5 7.2-4.5.6-23.5.4-26.9-.2-2.4-.7-3.1-2-3.1-2.3 0 0 0-.1.1-.1.3-.3.5-.5.7-.6 1.1-.4 2.4-.9 3.9-1.5 7.1-2.6 16.9-6 16.9-11.6.1-2.2-.5-4.2-1.9-5.6-1.5-1.6-3.8-2.5-6.6-2.4H58c-2.6 0-5.1 1.2-6.6 3.1L32.1 84l4.3 4.3L56.2 108l.1-.1h54.3v-.1l.1.1c7.2-5.7 30.7-24.6 33.3-28.8 1.5-2.5 1.4-5.6-.4-8zm-4.8 4.7l-.1.1c-1.5 2.3-16.7 15.1-30.9 26.5H59.1L40.6 84l15.9-15.9V68c.4-.3.9-.5 1.5-.5h31c.8 0 1.5.3 1.9.8.3.4.4.8.2 1.2-.5 1.6-2.1 2.3-3.7 2.9l-.6.2-11.3 3.8-.3.1v.1c-1 .5-2.2 1.2-3.4 2.4-1.7 1.6-2.4 3.8-1.9 6 .7 3 3.4 5.5 7.3 6.7h.2c4 .8 24.2.9 29.1.3 2.7-.3 6.9-3.2 14.1-8.2 5.4-3.7 13.5-9.3 16.1-9.6h.3l.3-.1c.6-.2 1.3.2 1.6.6.3.6 0 1-.1 1.1z"></path>
          <path fill="#D3D3D3" d="M143.8 79.1l.1-.1s0 .1-.1.1c.1 0 .1 0 0 0zM66 35.8l2.1 3.9 2.1-3.9 3.9-2.1-3.9-2.1-2.1-3.8-2.1 3.8-3.8 2.1m30.3-16l2.5-4.6 4.6-2.5L95 8.1l-2.5-4.6L90 8.1l-4.6 2.5 4.6 2.5m-29.1 2.6l1.6-2.9 2.9-1.5-2.9-1.6-1.6-2.9-1.5 2.9-2.9 1.6 2.9 1.5"></path>
        </svg>
      </div>
      <strong>Оплатите и получите удобным способом</strong>
    </div>
  </div>

</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="./resources/js/bootstrap.min.js"></script>

</body>
</html>