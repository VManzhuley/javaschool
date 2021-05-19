<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 08.05.2021
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Order confirmation</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">


    <jsp:include page="header.jsp"/>

    <div class="row g-1 justify-content-center text-center">
        <c:choose>
            <c:when test="${errorCode==404}">
                <p class="h3">There is no such page and never was </p>
                <p class="h6">Maybe there was, but not now. And this is unfortunate. But don't despair, find the right page!</p>
                <img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621338251/404_dhv8cd.jpg">
            </c:when>
            <c:when test="${errorCode==400}">
                <p class="h3">Bad Request</p>
                <img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621338249/400_ng8qup.jpg">
            </c:when>
            <c:when test="${errorCode==403}">
                <p class="h3">You don't have access to this page</p>
                <p class="h6">Maybe you need to log in?</p>
                <img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621418673/403_nn9mvr.jpg">
            </c:when>
            <c:when test="${errorCode==500}">
                <p class="h3">Oops, something went wrong on the server</p>
                <p class="h6">We are trying our best to fix it</p>
                <img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621338249/400_ng8qup.jpg">
            </c:when>
            <c:otherwise>
                <p class="h3">Oops! Something went wrong</p>
                <img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621338248/error_zejzg8.jpg">
            </c:otherwise>

        </c:choose>

    </div>


</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
