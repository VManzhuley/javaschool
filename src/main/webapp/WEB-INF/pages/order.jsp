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

            <form class="needs-validation" method="post">
                <div class="row g-3">
                    <h4 class="mb-3">Contact details</h4>
                    <div class="col-sm-6">
                        <label for="firstName" class="form-label">First name</label>
                        <spring:bind path="client.name">
                            <input type="text" class="form-control" id="firstName" name="${status.expression}" required>
                        </spring:bind>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <label for="lastName" class="form-label">Last name</label>
                        <spring:bind path="client.lastname">
                            <input type="text" class="form-control" id="lastName" name="${status.expression}" required>
                        </spring:bind>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="email" class="form-label">Email</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text">@</span>
                            <spring:bind path="client.email">
                                <input type="email" class="form-control" id="email" name="${status.expression}"
                                       placeholder="you@example.com">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid email address.
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="phone" class="form-label">Phone</label>
                        <div class="input-group has-validation">
                            <span class="input-group-text">+7</span>
                            <spring:bind path="client.phone">
                                <input type="tel" class="form-control" id="phone" name="${status.expression}"
                                       placeholder="912 345 67 89">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid phone.
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <div class="col-md-6">
                        <label for="payment" class="form-label">Payment method</label>
                        <spring:bind path="order.payment">
                            <select class="form-select" id="payment" required name="${status.expression}"
                                    onchange="if (this.selectedIndex===3){document.getElementById('DAnother').setAttribute('disabled', 'disabled');}
                                            else {document.getElementById('DAnother').removeAttribute('disabled');}">
                                <option disabled>Choose...</option>
                                <option id="PBank" value="Bank Transfer">Bank Transfer</option>
                                <option id="PCard" value="Credit Cards">Credit Cards</option>
                                <option id="PCash" value="Cash upon receipt">Cash upon receipt</option>
                            </select>
                        </spring:bind>
                    </div>
                    <div class="col-md-6">
                        <label for="delivery" class="form-label">Delivery terms</label>
                        <spring:bind path="order.shipping">
                            <select class="form-select" id="delivery" required name="${status.expression}"
                                    onchange="if (this.selectedIndex===3){document.getElementById('PCash').setAttribute('disabled', 'disabled');}
                                            else {document.getElementById('PCash').removeAttribute('disabled');}
                                            if (this.selectedIndex===1){document.getElementById('Shipping').setAttribute('style', 'display: none');}
                                            else{document.getElementById('Shipping').removeAttribute('style');}">
                                <option disabled>Choose...</option>
                                <option id="DSelf" value="Self pick-up">Self pick-up</option>
                                <option id="DSPb" value="In St. Petersburg">In St. Petersburg</option>
                                <option id="DAnother" value="To another city">To another city</option>
                            </select>
                        </spring:bind>
                    </div>


                    <div class="row g-3" id="Shipping" style="display: none">
                        <hr class="my-4">
                        <h4 class="mb-3">Shipping address</h4>

                        <div class="col-md-3">
                            <label for="zip" class="form-label">Zip</label>
                            <spring:bind path="client.zip">
                                <input type="text" class="form-control" id="zip" name="${status.expression}">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid zip code.
                            </div>
                        </div>

                        <div class="col-md-4">
                            <label for="country" class="form-label">Country</label>
                            <spring:bind path="client.country">
                                <input type="text" class="form-control" id="country" name="${status.expression}">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid country.
                            </div>
                        </div>

                        <div class="col-md-5">
                            <label for="city" class="form-label">City</label>
                            <spring:bind path="client.city">
                                <input type="text" class="form-control" id="city" name="${status.expression}">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid city.
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="street" class="form-label">Street</label>
                            <spring:bind path="client.street">
                                <input type="text" class="form-control" id="street" name="${status.expression}">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid street.
                            </div>
                        </div>


                        <div class="col-md-3">
                            <label for="building" class="form-label">Building</label>
                            <spring:bind path="client.building">
                                <input type="text" class="form-control" id="building" name="${status.expression}">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid building.
                            </div>
                        </div>

                        <div class="col-md-3">
                            <label for="apartment" class="form-label">Apartment</label>
                            <spring:bind path="client.apartment">
                                <input type="text" class="form-control" id="apartment" name="${status.expression}">
                            </spring:bind>
                            <div class="invalid-feedback">
                                Please enter a valid apartment.
                            </div>
                        </div>


                    </div>
                    <hr class="my-4">
                </div>

                <button type="submit" class="w-100 btn btn-primary btn-lg">Add To
                    Cart
                </button>
            </form>
        </div>
    </div>


</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
