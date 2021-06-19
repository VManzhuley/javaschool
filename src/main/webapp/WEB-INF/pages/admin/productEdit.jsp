<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <link href="/assets/css/dashboard.css" rel="stylesheet">
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="sidebarProducs.jsp"/>


        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 p-3">
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
                                <c:set value="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269387/product-foto_k0ilyl.png"
                                       var="productPhoto"/>
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
                            <div class="col-md-6">
                                <label class="form-label">Category</label>
                                <spring:bind path="productAbs.idCategory">
                                    <select class="form-select" name="${status.expression}">
                                        <c:forEach var="category" items="${categories}">
                                            <option ${productAbs.idCategory == category.idCategory ? yes : no}
                                                    value="${category.idCategory}">${category.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </spring:bind>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">or create new</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="categoryNameNew">
                                    <input type="submit" class="btn btn-sm btn-secondary" name="createCategoryNew" value="New"/>
                                </div>
                            </div>
                            <div class="col-md-6">
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
                            <div class="col-md-6">
                                <label class="form-label">or create new</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="descriptionNameNew">
                                    <input type="submit" class="btn btn-sm btn-secondary" name="createDescriptionNew" value="New"/>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Composition</label>

                                <spring:bind path="productAbs.idComposition">
                                    <select class="form-select" name="${status.expression}">
                                        <c:forEach var="composition" items="${compositions}">
                                            <option ${productAbs.idComposition == composition.id ? yes : no}
                                                    value="${composition.id}">${composition.name}</option>
                                        </c:forEach>
                                    </select>
                                </spring:bind>
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">or create new</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="compositionNameNew">
                                    <input type="submit" class="btn btn-sm btn-secondary" name="createCompositionNew" value="New"/>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div class="col-md-4 col-lg-3">

                        <div class="row g-1">
                            <h4 class="mb-3">Colour details</h4>
                            <div class="col-md-5">
                                <label class="form-label">Colour Main</label>
                            </div>
                            <div class="col-md-5">
                                <label class="form-label">Colour Secondary</label>
                            </div>

                            <c:forEach var="colourP" items="${productAbs.colours}" varStatus="i">

                                <div class="col-md-5">
                                    <input type="text" class="form-control" value="${colourP.colourMain}" disabled>
                                </div>


                                <div class="col-md-5">
                                    <input type="text" class="form-control" value="${colourP.colourSec}" disabled>
                                </div>
                                <div class="col-md-2">
                                    <c:if test="${colourP.idPhoto==0}">
                                        <a href="/admin/products/deleteColour?n=${i.index}" class="btn">x</a>
                                    </c:if>
                                </div>

                            </c:forEach>


                            <div class="col-md-5">
                                <spring:bind path="colour.colourMain">
                                    <select class="form-select" name="${status.expression}">
                                        <option value="" selected>---</option>
                                        <c:forEach var="colours" items="${colours}">
                                            <option value="${colours.name}">${colours.id}/${colours.name}</option>
                                        </c:forEach>
                                    </select>
                                </spring:bind>
                            </div>
                            <div class="col-md-5">
                                <spring:bind path="colour.colourSec">
                                    <select class="form-select" name="${status.expression}">
                                        <option value="" selected>---</option>
                                        <c:forEach var="colours" items="${colours}">
                                            <option value="${colours.name}">${colours.id}/${colours.name}</option>
                                        </c:forEach>
                                    </select>
                                </spring:bind>
                            </div>
                            <div class="col-md-2"></div>
                            <spring:hasBindErrors name="productAbs">
                                <c:forEach var="error" items="${errors.getFieldErrors('colours')}">
                                    <div class="row">
                                        <small class="text-danger"><spring:message message="${error}"/></small>
                                    </div>
                                </c:forEach>
                            </spring:hasBindErrors>
                            <button type="submit" class="w-100 btn btn-secondary btn-sm">Add colour</button>
                        </div>

                    </div>
                    <div class="col-md-4 col-lg-2">
                        <div class="row g-1">
                            <h4 class="mb-3">Size details</h4>
                            <div class="col-md-6">
                                <label class="form-label">Size</label>
                            </div>

                            <c:forEach var="sizeP" items="${productAbs.sizes}" varStatus="i">
                                <div class="col-md-11">
                                    <input type="text" class="form-control" value="${sizeP.size}" disabled>
                                </div>
                                <div class="col-md-1">
                                    <c:if test="${sizeP.idWV==0}">
                                        <a href="/admin/products/deleteSize?n=${i.index}" class="btn">x</a>
                                    </c:if>
                                </div>
                            </c:forEach>

                            <spring:bind path="size.size">
                                <div class="col-md-11">
                                    <select class="form-select" name="${status.expression}">
                                        <option value="" selected>---</option>
                                        <c:forEach var="size" items="${sizes}">
                                            <option value="${size.name}">${size.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </spring:bind>
                            <div class="col-md-1"></div>
                            <spring:hasBindErrors name="productAbs">
                                <c:forEach var="error" items="${errors.getFieldErrors('sizes')}">
                                    <div class="row">
                                        <small class="text-danger"><spring:message message="${error}"/></small>
                                    </div>
                                </c:forEach>
                            </spring:hasBindErrors>

                            <button type="submit" class="w-100 btn btn-secondary btn-sm">Add size</button>
                        </div>

                    </div>

                </div>


                <hr class="my-4">

                <input type="submit" name="submit" value="Save all changes and continue"
                       class="w-100 btn btn-outline-dark ${empty productAbs.colours ? 'disabled' : ''} ${empty productAbs.sizes ? 'disabled' : ''}"/>
            </form>
        </main>
    </div>

</div>

</body>
</html>
