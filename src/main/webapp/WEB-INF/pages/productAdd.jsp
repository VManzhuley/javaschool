<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 10.05.2021
  Time: 1:16
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


    <jsp:include page="headerAdmin.jsp"/>
    <form method="post">
        <div class="row g-3">

            <div class="col-md-4 col-lg-3">
                <div class="row g-1">
                    <h4 class="mb-3">Photo</h4>
                    <label class="form-label">Photo Link</label>
                    <div class="input-group mb-3">
                        <spring:bind path="productAbs.photoLink">
                            <input type="text" class="form-control" value="${productAbs.photoLink}"
                                   name="${status.expression}">
                            <button class="btn btn-outline-primary" type="button">Choose</button>
                        </spring:bind>
                    </div>
                    <img src="/assets/img/product${productAbs.photoLink}" class="rounded mx-auto d-block"
                         style="width: 17rem;" alt="${productAbs.name}">
                </div>
            </div>
            <div class="col-md-4 col-lg-4">
                <div class="row g-1">
                    <h4 class="mb-3">Product details</h4>
                    <div class="col-md-6">
                        <label class="form-label">Article</label>
                        <spring:bind path="productAbs.article">
                            <input type="text" class="form-control" name="${status.expression}"
                                   value="${productAbs.article}">
                        </spring:bind>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Price</label>
                        <spring:bind path="productAbs.price">
                            <input type="text" class="form-control" name="${status.expression}"
                                   value="${productAbs.price}">
                        </spring:bind>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Name</label>
                        <spring:bind path="productAbs.name">
                            <input type="text" class="form-control" name="${status.expression}"
                                   value="${productAbs.name}">
                        </spring:bind>
                    </div>
                    <c:set value="selected" var="yes"/>
                    <c:set value="" var="no"/>
                    <div class="col-md-12">
                        <label class="form-label">Category</label>
                        <spring:bind path="productAbs.idCategory">
                            <select class="form-select" name="${status.expression}">
                                <c:forEach var="category" items="${categories}">
                                    <option ${productAbs.idCategory == category.idCategory ? yes : no}
                                            value="${category.idCategory}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Description</label>

                        <spring:bind path="productAbs.idDescription">
                            <select class="form-select" name="${status.expression}">
                                <c:forEach var="description" items="${descriptions}">
                                    <option ${productAbs.idDescription == description.id ? yes : no}
                                            value="${description.id}">${description.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Composition</label>

                        <spring:bind path="productAbs.idComposition">
                            <select class="form-select" name="${status.expression}">
                                <c:forEach var="compositions" items="${compositions}">
                                    <option ${productAbs.idComposition == compositions.id ? yes : no}
                                            value="${compositions.id}">${compositions.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>


                </div>
            </div>
            <div class="col-md-4 col-lg-3">

                <div class="row g-1">
                    <h4 class="mb-3">Colour details</h4>
                    <div class="col-md-6">
                        <label class="form-label">Colour Main</label>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Colour Secondary</label>
                    </div>

                    <c:forEach var="colourP" items="${productAbs.colours}">

                        <div class="col-md-6">
                            <input type="text" class="form-control" value="${colourP.colourMain}" disabled>
                        </div>


                        <div class="col-md-6">
                            <input type="text" class="form-control" value="${colourP.colourSec}" disabled>
                        </div>

                    </c:forEach>


                    <div class="col-md-6">
                        <spring:bind path="colour.colourMain">
                            <select class="form-select" name="${status.expression}">
                                <option value="">---</option>
                                <c:forEach var="colours" items="${colours}">
                                    <option value="${colours.name}">${colours.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="col-md-6">
                        <spring:bind path="colour.colourSec">
                            <select class="form-select" name="${status.expression}">
                                <option value="">---</option>
                                <c:forEach var="colours" items="${colours}">
                                    <option value="${colours.name}">${colours.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <button type="submit" class="w-100 btn btn-primary btn-sm">Add colour</button>
                </div>

            </div>
            <div class="col-md-4 col-lg-2">
                <div class="row g-1">
                    <h4 class="mb-3">Size details</h4>
                    <div class="col-md-6">
                        <label class="form-label">Size</label>
                    </div>

                    <c:forEach var="sizeP" items="${productAbs.sizes}">
                        <div class="col-md-12">
                            <input type="text" class="form-control" value="${sizeP.size}" disabled>
                        </div>
                    </c:forEach>

                    <spring:bind path="size.size">
                        <div class="col-md-12">
                            <select class="form-select" name="${status.expression}">
                                <option value="">---</option>
                                <c:forEach var="size" items="${sizes}">
                                    <option value="${size.name}">${size.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </spring:bind>

                    <button type="submit" class="w-100 btn btn-primary btn-sm">Add size</button>
                </div>

            </div>

        </div>
    </form>
    <hr class="my-4">
<form method="post">
    <table class="table align-middle text-center">
        <thead>
        <tr>
            <th scope="col">Colour</th>
            <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="i">
                <th scope="col">${sizeTable.size}</th>
                <c:set var="ss" value="${i.index}"/>
            </c:forEach>
            <th scope="col">PhotoLink</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="colourTable" items="${productAbs.colours}" varStatus="i">
            <tr>
                <th>${colourTable.colourMain}<br>${colourTable.colourSec}</th>




                <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="j">
                    <spring:bind path="productAbs.products[${i.index*(ss+1)+j.index}].quantity">
                    <td><input class="form-control form-control-plaintext text-center" type="number" min="0" value="0" name="${status.expression}">
                    </td>
                    </spring:bind>
                </c:forEach>

                <td>
                    <div class="input-group mb-1">
                        <input type="text" class="form-control">
                        <button class="btn btn-outline-primary" type="button">Choose</button>
                    </div>
                    <img src="/assets/img/product" class="rounded mx-auto d-block"
                         style="width: 5rem;" alt="...">
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>

        <tr>
            <th>Weight</th>
            <c:forEach var="sizeTable" items="${productAbs.sizes}">
                <td><input class="form-control form-control-plaintext text-center" type="number" min="0" value="0"></td>
            </c:forEach>

        </tr>
        <tr>
            <th>Volume</th>
            <c:forEach var="sizeTable" items="${productAbs.sizes}">
                <td><input class="form-control form-control-plaintext text-center" type="number" min="0" value="0"></td>
            </c:forEach>
        </tr>
        </tfoot>

    </table>
<input type="submit">
</form>

</div>

<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
