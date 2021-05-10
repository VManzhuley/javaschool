<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 09.05.2021
  Time: 22:06
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

    <div class="row g-3">
        <div class="col-md-4 col-lg-4">
            <div class="row g-1">
                <h4 class="mb-3">Order details</h4>
                <div class="col-sm-4">
                    <label class="form-label">Number</label>
                    <input type="text" class="form-control" value="${order.id}" disabled>
                </div>
                <div class="col-sm-8">
                    <label class="form-label">Date</label>
                    <input type="text" class="form-control" value="${order.date}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Payment</label>
                    <input type="text" class="form-control" value="${order.payment}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Delivery terms</label>
                    <input type="text" class="form-control" value="${order.shipping}" disabled>
                </div>

                <label class="form-label">Status</label>

                <form method="post">
                    <div class="row g-1">
                        <div class="col-sm-8">

                            <c:set value="selected" var="yes"/>
                            <c:set value="" var="no"/>
                            <select class="form-select" id="delivery" name="status">
                                <option value="New" ${order.status == 'New' ? yes : no}>New</option>
                                <option value="Waiting for payment" ${order.status == 'Waiting for payment' ? yes : no}>
                                    Waiting for payment
                                </option>
                                <option value="Transferred to TC" ${order.status == 'Transferred to TC' ? yes : no}>
                                    Transferred to TC
                                </option>
                                <option value="Completed" ${order.status == 'Completed' ? yes : no}>Completed</option>
                                <option value="Canceled" ${order.status == 'Canceled' ? yes : no}>Canceled</option>
                            </select>
                        </div>
                        <div class="col-sm-4">
                            <button type="submit" class="form-control btn btn-outline-primary">Update</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div class="col-md-4 col-lg-4 ">
            <div class="row g-1">
                <h4 class="mb-3">Contact details</h4>

                <div class="col-sm-12">
                    <label class="form-label">Name</label>
                    <input type="text" class="form-control" value="${order.client.name}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Lastname</label>
                    <input type="text" class="form-control" value="${order.client.lastname}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Email</label>
                    <input type="text" class="form-control" value="${order.client.email}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Phone</label>
                    <input type="text" class="form-control" value="${order.client.phone}" disabled>
                </div>

            </div>

        </div>
        <div class="col-md-4 col-lg-4">
            <div class="row g-1">

                <h4 class="mb-3">Shipping address</h4>
                <div class="col-sm-6">
                    <label class="form-label">Zip</label>
                    <input type="text" class="form-control" value="${order.client.zip}" disabled>
                </div>
                <div class="col-sm-6">
                    <label class="form-label">Country</label>
                    <input type="text" class="form-control" value="${order.client.country}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">City</label>
                    <input type="text" class="form-control" value="${order.client.city}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Street</label>
                    <input type="text" class="form-control" value="${order.client.street}" disabled>
                </div>
                <div class="col-sm-6">
                    <label class="form-label">Building</label>
                    <input type="text" class="form-control" value="${order.client.building}" disabled>
                </div>
                <div class="col-sm-6">
                    <label class="form-label">Apartment</label>
                    <input type="text" class="form-control" value="${order.client.apartment}" disabled>
                </div>
            </div>


        </div>

        <hr class="my-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Article</th>
                <th scope="col">Product</th>
                <th scope="col">Price, RUB</th>
                <th scope="col">Quantity</th>
                <th scope="col">In Stock</th>
                <th scope="col">Total, RUB</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${order.productOrderedList}" varStatus="i">
                <tr>
                    <th scope="row">${i.index+1}</th>
                    <td>${product.product.article}</td>
                    <td>${product.product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.product.quantity}</td>
                    <td>${product.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
