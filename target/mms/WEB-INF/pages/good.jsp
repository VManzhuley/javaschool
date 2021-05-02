<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 28.04.2021
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="col-md-5 offset-md-2">
                <div id="carousel" class="carousel slide" data-bs-interval="6000" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="image/soccer/gaiters/calcio/c001_0001.jpg">
                        </div>
                        <div class="carousel-item">
                            <img src="image/soccer/gaiters/calcio/c001_0002.jpg">
                        </div>
                        <div class="carousel-item">
                            <img src="image/soccer/gaiters/calcio/c001_0003.jpg">
                        </div>
                        <div class="carousel-item">
                            <img src="image/soccer/gaiters/calcio/c001_0004.jpg">
                        </div>
                        <div class="carousel-item">
                            <img src="image/soccer/gaiters/calcio/c001_0005.jpg">
                        </div>
                        <div class="carousel-item">
                            <img src="image/soccer/gaiters/calcio/c001_0007.jpg">
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <div id="thumbcarousel" class="carousel slide" data-bs-interval="12000" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <div data-bs-target="#carousel" data-bs-slide-to="0" class="img-thumbnail">
                                    <img src="image/soccer/gaiters/calcio/c001_0001.jpg">
                                </div>
                                <div data-bs-target="#carousel" data-bs-slide-to="1" class="img-thumbnail">
                                    <img src="image/soccer/gaiters/calcio/c001_0002.jpg">
                                </div>
                                <div data-bs-target="#carousel" data-bs-slide-to="2" class="img-thumbnail">
                                    <img src="image/soccer/gaiters/calcio/c001_0003.jpg">
                                </div>
                                <div data-bs-target="#carousel" data-bs-slide-to="3" class="img-thumbnail">
                                    <img src="image/soccer/gaiters/calcio/c001_0004.jpg">
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div data-bs-target="#carousel" data-bs-slide-to="4" class="img-thumbnail">
                                    <img src="image/soccer/gaiters/calcio/c001_0005.jpg">
                                </div>
                                <div data-bs-target="#carousel" data-bs-slide-to="5" class="img-thumbnail">
                                    <img src="image/soccer/gaiters/calcio/c001_0007.jpg">
                                </div>
                            </div>
                        </div>
                        <button class="carousel-control-prev carousel-dark" type="button" data-bs-target="#thumbcarousel" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" ></span>
                        </button>
                        <button class="carousel-control-next carousel-dark" type="button" data-bs-target="#thumbcarousel" data-bs-slide="next">
                            <span class="carousel-control-next-icon" ></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9">

        </div>
    </div>
</div>
</body>
</html>
