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


    <jsp:include page="header.jsp"/>
    <form method="post">
        <div class="row g-3">

            <div class="col-md-4 col-lg-3">
                <div class="row g-1">
                    <h4 class="mb-3">Photo</h4>
                    <label class="form-label">Photo Link</label>
                    <div class="input-group mb-3">
                        <spring:bind path="productAbs.photoLink">
                            <input type="text" class="form-control" value="${productAbs.photoLink}"
                                   name="${status.expression}"
                                   onchange="document.getElementById('photoLink').setAttribute('src',this.value);">
                        </spring:bind>
                    </div>
                    <c:set value="${productAbs.photoLink}" var="productPhoto"/>
                    <c:if test="${productAbs.photoLink.length()<1}">
                        <c:set value="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269387/product-foto_k0ilyl.png" var="productPhoto"/>
                    </c:if>
                    <img src="${productPhoto}" class="rounded mx-auto d-block"
                         style="width: 17rem;" alt="${productAbs.name}" id="photoLink">
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
                        <spring:hasBindErrors name="productAbs">
                            <c:forEach var="error" items="${errors.getFieldErrors('article')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Price</label>
                        <spring:bind path="productAbs.price">
                            <input type="number" class="form-control" name="${status.expression}"
                                   value="${productAbs.price}">
                        </spring:bind>
                        <spring:hasBindErrors name="productAbs">
                            <c:forEach var="error" items="${errors.getFieldErrors('price')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>


                    </div>
                    <div class="col-md-12">
                        <label class="form-label">Name</label>
                        <spring:bind path="productAbs.name">
                            <input type="text" class="form-control" name="${status.expression}"
                                   value="${productAbs.name}">
                        </spring:bind>
                        <spring:hasBindErrors name="productAbs">
                            <c:forEach var="error" items="${errors.getFieldErrors('name')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
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
                                <option value="" selected>---</option>
                                <c:forEach var="colours" items="${colours}">
                                    <option value="${colours.name}">${colours.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="col-md-6">
                        <spring:bind path="colour.colourSec">
                            <select class="form-select" name="${status.expression}">
                                <option value="" selected>---</option>
                                <c:forEach var="colours" items="${colours}">
                                    <option value="${colours.name}">${colours.name}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <spring:hasBindErrors name="productAbs">
                        <c:forEach var="error" items="${errors.getFieldErrors('colours')}">
                            <div class="row">
                                <small class="text-danger"><spring:message message="${error}"/></small>
                            </div>
                        </c:forEach>
                    </spring:hasBindErrors>
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
                                <option value="" selected>---</option>
                                <c:forEach var="size" items="${sizes}">
                                    <option value="${size.name}">${size.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </spring:bind>
                    <spring:hasBindErrors name="productAbs">
                        <c:forEach var="error" items="${errors.getFieldErrors('sizes')}">
                            <div class="row">
                                <small class="text-danger"><spring:message message="${error}"/></small>
                            </div>
                        </c:forEach>
                    </spring:hasBindErrors>

                    <button type="submit" class="w-100 btn btn-primary btn-sm">Add size</button>
                </div>

            </div>

        </div>
    </form>

    <hr class="my-4">

    <a href="/admin/product-add-confirm"
       class="w-100 btn btn-primary ${empty productAbs.colours ? 'disabled' : ''} ${empty productAbs.sizes ? 'disabled' : ''}">Create
        Product</a>


</div>

<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
