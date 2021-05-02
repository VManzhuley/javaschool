
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp" />
        </div>


        <div class="col-md-9">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach var="productAbs" items="${productAbsList}">
                    <div class="col">
                        <div class="card h-100">
                            <img src="/assets/img/product${productAbs.photoLink}" class="rounded mx-auto d-block"
                                 style="width: 18rem;">
                            <div class="card-body">
                                <h5 class="card-title"><a href="/product/${productAbs.id}">${productAbs.name}</a>
                                </h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${productAbs.article}</li>
                                    <li class="list-group-item">${productAbs.price}</li>
                                    <li class="list-group-item">${productAbs.description}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script src="/assets/js/bootstrap.min.js" ></script>
</body>
</html>



