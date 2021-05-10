<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 08.05.2021
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <jsp:include page="header.jsp"/>

    <div class="text-center">
        <p class="h3">Thank you!</p>
        <p class="h4">Your order number is ${id}</p>
        <p class="h5">Our managers are fighting for your order and will contact you by mail shortly.</p>
    </div>

</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
