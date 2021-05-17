<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 08.05.2021
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Order confirmation</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<c:if test="${empty cart.cartItems}">
    <c:redirect url="/"/>
</c:if>
<div class="container">


    <jsp:include page="header.jsp"/>

    <div class="row g-5">
        <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-primary">Your cart</span>

            </h4>
            <ul class="list-group mb-3">
                <c:forEach var="cartItem" items="${cart.cartItems}">
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <h6 class="my-0">${cartItem.product.article}</h6>
                            <small class="text-muted">Qty: ${cartItem.quantity}</small>
                        </div>
                        <span class="text-muted">${cartItem.amount} RUB</span>
                    </li>
                </c:forEach>

                <li class="list-group-item d-flex justify-content-between">
                    <span>Total </span>
                    <strong>${cart.amountTotal} RUB</strong>
                </li>
            </ul>
            <a href="/cart" class="btn btn-outline-primary">
                <span>Back to cart</span>
            </a>


        </div>
        <div class="col-md-7 col-lg-8">

            <form method="post">
                <div class="row g-3">
                    <h4 class="mb-3">Contact details</h4>
                    <div class="col-sm-6">
                        <label for="firstName" class="form-label">First name</label>
                        <spring:bind path="client.name">
                            <input type="text" class="form-control" id="firstName" name="${status.expression}"
                                   value="${client.name}">
                        </spring:bind>
                        <spring:hasBindErrors name="client">
                            <c:forEach var="error" items="${errors.getFieldErrors('name')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                    </div>

                    <div class="col-sm-6">
                        <label for="lastName" class="form-label">Last name</label>
                        <spring:bind path="client.lastname">
                            <input type="text" class="form-control" id="lastName" name="${status.expression}"
                                   value="${client.lastname}">
                        </spring:bind>
                        <spring:hasBindErrors name="client">
                            <c:forEach var="error" items="${errors.getFieldErrors('lastname')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                    </div>

                    <div class="col-md-6">
                        <label for="email" class="form-label">Email</label>
                        <div class="input-group">
                            <span class="input-group-text">@</span>
                            <spring:bind path="client.email">
                                <input type="email" class="form-control" id="email" name="${status.expression}"
                                       value="${client.email}" placeholder="dsfsd@sdfsdf">
                            </spring:bind>
                        </div>
                        <spring:hasBindErrors name="client">
                            <c:forEach var="error" items="${errors.getFieldErrors('email')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                        <c:if test="${not empty message}">
                            <div class="row">
                                <small class="text-danger">${message}</small>
                            </div>
                        </c:if>
                    </div>

                    <div class="col-md-6">
                        <label for="phone" class="form-label">Phone</label>
                        <div class="input-group">
                            <span class="input-group-text">+7</span>
                            <spring:bind path="client.phone">
                                <input type="tel" class="form-control" id="phone" name="${status.expression}"
                                       value="${client.phone}" placeholder="912 345 67 89">
                            </spring:bind>
                        </div>
                        <spring:hasBindErrors name="client">
                            <c:forEach var="error" items="${errors.getFieldErrors('phone')}">
                                <div class="row">
                                    <small class="text-danger"><spring:message message="${error}"/></small>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                    </div>

                    <hr class="my-4">

                    <div class="col-md-6">
                        <label for="payment" class="form-label">Payment method</label>
                        <spring:bind path="order.payment">
                            <select class="form-select" id="payment" required name="${status.expression}"
                                    onchange="if (this.value==='CASH'){document.getElementById('ANOTHER').setAttribute('disabled', 'disabled');}
                                            else {document.getElementById('ANOTHER').removeAttribute('disabled');}">

                                <c:forEach var="payment" items="${paymentType}">
                                    <option value="${payment}" id="${payment}" ${order.payment == payment ? 'selected' : ''}>${payment.title}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="col-md-6">
                        <label for="delivery" class="form-label">Delivery terms</label>
                        <spring:bind path="order.shipping">
                            <select class="form-select" id="delivery" required name="${status.expression}"
                                    onchange="if (this.value==='ANOTHER'){document.getElementById('CASH').setAttribute('disabled', 'disabled');}
                                            else {document.getElementById('CASH').removeAttribute('disabled');}
                                            if (this.value==='SELF'){document.getElementById('Shipping').setAttribute('style', 'display: none');}
                                            else{document.getElementById('Shipping').removeAttribute('style');}">

                                <c:forEach var="shipping" items="${shippingType}">
                                    <option value="${shipping}" id="${shipping}" ${order.shipping == shipping ? 'selected' : ''}>${shipping.title}</option>
                                </c:forEach>
                            </select>
                        </spring:bind>
                    </div>
                    <spring:bind path="client.password">
                        <input type="tel" hidden name="${status.expression}">
                    </spring:bind>
                    <spring:bind path="client.matchingPassword">
                        <input type="tel" hidden name="${status.expression}">
                    </spring:bind>

                    <div class="row g-3" id="Shipping" style="display: ${order.shipping.name() != 'SELF' ? '' : 'none'}">
                        <hr class="my-4">
                        <h4 class="mb-3">Shipping address</h4>

                        <div class="col-md-3">
                            <label for="zip" class="form-label">Zip</label>
                            <spring:bind path="client.zip">
                                <input type="text" class="form-control" id="zip" name="${status.expression}"
                                       value="${client.zip}">
                            </spring:bind>
                        </div>

                        <div class="col-md-4">
                            <label for="country" class="form-label">Country</label>
                            <spring:bind path="client.country">
                                <input type="text" class="form-control" id="country" name="${status.expression}"
                                       value="${client.country}">
                            </spring:bind>
                        </div>

                        <div class="col-md-5">
                            <label for="city" class="form-label">City</label>
                            <spring:bind path="client.city">
                                <input type="text" class="form-control" id="city" name="${status.expression}"
                                       value="${client.city}">
                            </spring:bind>
                        </div>
                        <div class="col-md-6">
                            <label for="street" class="form-label">Street</label>
                            <spring:bind path="client.street">
                                <input type="text" class="form-control" id="street" name="${status.expression}"
                                       value="${client.street}">
                            </spring:bind>
                        </div>


                        <div class="col-md-3">
                            <label for="building" class="form-label">Building</label>
                            <spring:bind path="client.building">
                                <input type="text" class="form-control" id="building" name="${status.expression}"
                                       value="${client.building}">
                            </spring:bind>
                        </div>

                        <div class="col-md-3">
                            <label for="apartment" class="form-label">Apartment</label>
                            <spring:bind path="client.apartment">
                                <input type="text" class="form-control" id="apartment" name="${status.expression}"
                                       value="${client.apartment}">
                            </spring:bind>
                        </div>


                    </div>
                    <hr class="my-4">
                </div>

                <button type="submit" class="w-100 btn btn-primary btn-lg">Confirm order</button>
            </form>
        </div>
    </div>


</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
