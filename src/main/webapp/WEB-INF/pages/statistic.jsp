<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 16.05.2021
  Time: 14:52
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

    <section class="container py-5">
        <div class="row">
            <div class="col-12 col-md-3 p-5 mt-3">
                <a href="/admin/statistic/products"><img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269394/products_xu4b2l.jpg"
                                                            class="rounded-circle img-fluid border"></a>
                <h5 class="text-center mt-3 mb-3">Products</h5>

            </div>
            <div class="col-12 col-md-3 p-5 mt-3">
                <a href="/admin/statistic/clients"><img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269763/clients_tat1zm.jpg"
                                                           class="rounded-circle img-fluid border"></a>
                <h2 class="h5 text-center mt-3 mb-3">Clients</h2>

            </div>
            <div class="col-12 col-md-3 p-5 mt-3">
                <a href="/admin/statistic/proceeds"><img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269393/proceeds_jawc1u.jpg"
                                                            class="rounded-circle img-fluid border"></a>
                <h2 class="h5 text-center mt-3 mb-3">Proceeds</h2>

            </div>
            <div class="col-12 col-md-3 p-5 mt-3">
                <a href="/admin/statistic/warehouse"><img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269763/warehouse_cwykir.jpg"
                                                              class="rounded-circle img-fluid border"></a>
                <h2 class="h5 text-center mt-3 mb-3">Warehouse</h2>

            </div>
        </div>
    </section>

</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>

