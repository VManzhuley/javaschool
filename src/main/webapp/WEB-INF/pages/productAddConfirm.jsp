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
                            <td><input class="form-control form-control-plaintext text-center" type="number" min="0"
                                       value="0" name="${status.expression}">
                            </td>
                        </spring:bind>
                    </c:forEach>

                    <td>
                        <div class="input-group mb-1">
                            <spring:bind path="productAbs.colours[${i.index}].photoLink">

                            <input type="text" class="form-control" name="${status.expression}"
                                   onchange="document.getElementById('productPhoto${i.index}').setAttribute('src',this.value)">

                            </spring:bind>
                        </div>
                        <img src="https://res.cloudinary.com/dwkkqpsi0/image/upload/v1621269387/product-foto_k0ilyl.png" class="rounded mx-auto d-block"
                             style="width: 5rem;" alt="..." id="productPhoto${i.index}">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>

            <tr>
                <th>Weight</th>
                <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="j">
                    <spring:bind path="productAbs.sizes[${j.index}].weight">
                    <td><input class="form-control form-control-plaintext text-center" type="number" min="0" value="0"
                               name="${status.expression}">
                    </td>
                    </spring:bind>
                </c:forEach>

            </tr>
            <tr>
                <th>Volume</th>
                <c:forEach var="sizeTable" items="${productAbs.sizes}" varStatus="j">
                    <spring:bind path="productAbs.sizes[${j.index}].volume">
                    <td><input class="form-control form-control-plaintext text-center" type="number" min="0" value="0"
                               name="${status.expression}">
                    </td>
                    </spring:bind>
                </c:forEach>
            </tr>
            </tfoot>

        </table>

        <input type="submit" class="w-100 btn btn-primary ${empty productAbs.products ? 'disabled' : ''}"
                value="Create Product">


    </form>

</div>

<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
