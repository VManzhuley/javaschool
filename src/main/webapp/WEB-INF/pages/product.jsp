<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>
<style>

    .carousel {
        margin-top: 20px;
    }

    .carousel-item .img-thumbnail {
        width: 25%;
        cursor: pointer;
        float: left;
    }

    .carousel-item .img-thumbnail img {
        width: 100%;
        margin: 2px;
    }

    .carousel-item img {
        width: 100%;
    }

</style>
<section class="bg-light">
    <div class="container pb-5">
        <div class="row">
            <div class="col-lg-5 mt-5">
                <div id="carousel" class="carousel slide" data-bs-interval="6000" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="photo" items="${productAbs.colours}" begin="0" end="0">
                            <div class="carousel-item active">
                                <img src="/assets/img/product${photo.photoLink}">
                            </div>
                        </c:forEach>
                        <c:forEach var="photo" items="${productAbs.colours}" begin="1">
                            <div class="carousel-item">
                                <img src="/assets/img/product${photo.photoLink}">
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="row">

                    <div class="col-1 align-self-center">
                        <c:if test="${productAbs.colours.size() gt 4}">
                            <a class="carousel-dark" href="#minicarousel" role="button" data-bs-slide="prev">
                                <span class="carousel-control-prev-icon"></span>
                            </a>
                        </c:if>
                    </div>

                    <div id="minicarousel" class="col-10 carousel slide" data-bs-interval="12000"
                         data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <c:forEach var="photo" items="${productAbs.colours}" begin="0" end="3" varStatus="i">
                                    <div data-bs-target="#carousel" data-bs-slide-to="${i.index}" class="img-thumbnail">
                                        <img src="/assets/img/product${photo.photoLink}">
                                    </div>
                                </c:forEach>
                            </div>
                            <c:if test="${productAbs.colours.size() gt 4}">
                                <c:forEach begin="4" end="${productAbs.colours.size()}" step="4" varStatus="i">
                                    <div class="carousel-item">
                                        <c:forEach var="photo" items="${productAbs.colours}" begin="${i.index}" end="${i.index+3}" varStatus="j">
                                            <div data-bs-target="#carousel" data-bs-slide-to="${j.index}"
                                                 class="img-thumbnail">
                                                <img src="/assets/img/product${photo.photoLink}">
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-1 align-self-center">
                        <c:if test="${productAbs.colours.size() gt 4}">
                            <a class="carousel-dark" href="#minicarousel" role="button" data-bs-slide="next">
                                <span class="carousel-control-next-icon"></span>
                            </a>
                        </c:if>
                    </div>

                </div>
            </div>
            <div class="col-lg-7 mt-5">
                <div class="card">
                    <div class="card-body">
                        <h1 class="h2">${productAbs.name}</h1>
                        <p class="h5 py-1">${productAbs.article}</p>
                        <p class="h3 py-2">${productAbs.price} rubles</p>
                        <p class="h5 py-1">${productAbs.composition}</p>


                        <div class="row pb-md-2">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="colour"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    Choose colour
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="colour">
                                    <c:forEach var="colours" items="${productAbs.colours}" varStatus="i">
                                        <li>
                                            <button class="dropdown-item" type="button" data-bs-target="#carousel"
                                                    data-bs-slide-to="${i.index}">${colours.name}
                                            </button>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="row pb-md-2">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="sizes"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    Choose size
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="sizes">
                                    <c:forEach var="size" items="${productAbs.sizes}">
                                    <li>
                                        <button class="dropdown-item" type="button">${size}</button>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="col d-grid">
                            <button type="submit" class="btn btn-primary btn-lg" name="submit" value="addtocard">Add To
                                Cart
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $('.dropdown-toggle').dropdown();
    });
</script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>


</body>
