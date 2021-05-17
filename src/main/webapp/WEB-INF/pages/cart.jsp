<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 07.05.2021
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<c:if test="${empty cart.cartItems}">
    <c:redirect url="/"/>
</c:if>
<div class="container">
    <jsp:include page="header.jsp"/>

    <table class="table align-middle text-center">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">Product</th>
            <th scope="col">Price, RUB</th>
            <th scope="col">Quantity</th>
            <c:if test="${cart.isMissQuantity}">
                <th scope="col">Miss Quantity</th>
            </c:if>
            <th scope="col">Total, RUB</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cartItem" items="${cart.cartItems}">
            <form method="post">
                <input type="hidden" name="idProduct" value="${cartItem.product.id}"/>
                <tr>
                    <td rowspan="2"><img src="${cartItem.product.colour.photoLink}" alt="..."
                                         style="width: 10rem;"></td>
                    <td rowspan="2">${cartItem.product.name}</td>
                    <td rowspan="2">${cartItem.product.price}</td>
                    <td rowspan="2">


                        <input class="form-control form-control-plaintext text-center" type="number" min="1"
                               name="quantity"
                               value="${cartItem.quantity}">


                    </td>
                    <c:if test="${cart.isMissQuantity}">
                        <c:if test="${cartItem.missQuantity>0}">
                            <td rowspan="2">${cartItem.missQuantity}</td>
                        </c:if>
                        <c:if test="${cartItem.missQuantity==0}">
                            <td rowspan="2"></td>
                        </c:if>
                    </c:if>
                    <td rowspan="2">${cartItem.amount}</td>
                    <td>
                        <button type="submit" class="btn btn-success">Update</button>
                    </td>

                </tr>
                <tr>
                    <td><a href="/deleteCartItem?id=${cartItem.product.id}" class="btn btn-outline-danger">
                        <span>Delete</span>
                    </a></td>
                </tr>
            </form>
        </c:forEach>

        <tr style="border: white">
            <td></td>
            <td></td>
            <td></td>
            <td><p class="h4"> TOTAL:</p></td>
            <td><p class="h5"> ${cart.amountTotal} RUB</p></td>
        </tr>


        </tbody>

    </table>

    <div class="row justify-content-md-center ">
        <c:if test="${cart.isMissQuantity}">
            <div class="text-center text-danger" >Unfortunately, some of the products are not available for order, please reduce their quantity</div>

        </c:if>
        <div class="col-sm-1"><a href="/orderConfirm" class="btn btn-primary btn-lg ${cart.isMissQuantity ? 'disabled' : ''}">
            <span>Continue</span>
        </a></div>
    </div>

    <c:if test="${empty cart.cartItems}">
        <c:redirect url="/"/>
    </c:if>


</div>


</body>
<script src="/assets/js/bootstrap.min.js"></script>
</html>
