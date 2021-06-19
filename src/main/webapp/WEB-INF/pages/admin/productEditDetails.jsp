<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <table class="table align-middle text-center">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="i">
                            <th scope="col">${sizeTable.size}</th>
                            <c:set var="indexS" value="${i.index}"/>
                        </c:forEach>
                        <th scope="col">PhotoLink</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="colourTable" items="${productAbs.colours}" varStatus="i">
                        <tr>
                            <th>${colourTable.colourMain}/<br>${colourTable.colourSec}</th>


                            <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="j">
                                <c:set var="indexP" value="${i.index*(indexS+1)+j.index}"/>

                                <td>
                                    <div class="input-group mb-3">
                                        <input class="form-control text-center" type="number"
                                               value="${productAbs.products[indexP].quantity}" disabled>
                                        <spring:bind path="productAbs.products[${i.index*(indexS+1)+j.index}].quantity">
                                            <input class="form-control text-center" type="number" value="0"
                                                   name="${status.expression}">
                                        </spring:bind>
                                    </div>
                                </td>

                            </c:forEach>

                            <td>
                                <c:set var="indexC" value="${i.index}"/>
                                <div class="input-group mb-1">
                                    <spring:bind path="productAbs.colours[${i.index}].photoLink">

                                        <input type="text" class="form-control" name="${status.expression}"
                                               value="${productAbs.colours[indexC].photoLink}"
                                               onchange="document.getElementById('productPhoto${i.index}').setAttribute('src',this.value)">

                                    </spring:bind>
                                </div>
                                <img src="${productAbs.colours[indexC].photoLink}"
                                     class="rounded mx-auto d-block"
                                     style="width: 5rem;" alt="..." id="productPhoto${i.index}">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>

                    <tr>
                        <th>Weight, g</th>
                        <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="j">
                            <c:set var="indexS" value="${j.index}"/>
                            <spring:bind path="productAbs.sizes[${j.index}].weight">
                                <td><input class="form-control form-control-plaintext text-center" type="number" min="0"
                                           value="${productAbs.sizes[indexS].weight}"
                                           name="${status.expression}">
                                </td>
                            </spring:bind>
                        </c:forEach>

                    </tr>
                    <tr>
                        <th>Volume, cm<sup>3</sup></th>
                        <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="j">
                            <c:set var="indexS" value="${j.index}"/>
                            <spring:bind path="productAbs.sizes[${j.index}].volume">
                                <td><input class="form-control form-control-plaintext text-center" type="number" min="0"
                                           value="${productAbs.sizes[indexS].volume}"
                                           name="${status.expression}">
                                </td>
                            </spring:bind>
                        </c:forEach>
                    </tr>
                    </tfoot>

                </table>
                <div class="input-group mb-3">

                    <input type="submit"
                           class="w-50 btn btn-outline-dark ${empty productAbs.products ? 'disabled' : ''}"
                           name="ChangePublish"
                           value="${productAbs.outdated ? 'Save and Publish':'Save and Unpublish'}">

                    <input type="submit"
                           class="w-50 btn btn-outline-secondary ${empty productAbs.products ? 'disabled' : ''}"
                           value="Just Save">
                </div>


            </form>
        </main>
    </div>

</div>

</body>
</html>
