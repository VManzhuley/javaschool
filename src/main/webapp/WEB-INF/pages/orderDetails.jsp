<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <sec:authorize access="hasRole('ADMIN')">
        <link href="/assets/css/dashboard.css" rel="stylesheet">
    </sec:authorize>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
</head>
<body>


<sec:authorize access="hasRole('USER')">
<div class="container">
        <jsp:include page="header.jsp"/>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMIN')">
        <jsp:include page="admin/headerAdmin.jsp"/>
    <div class="container-fluid">

        </sec:authorize>

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
                    <input type="text" class="form-control" value="${order.payment.title}" disabled>
                </div>
                <div class="col-sm-12">
                    <label class="form-label">Delivery terms</label>
                    <input type="text" class="form-control" value="${order.shipping.title}" disabled>
                </div>

                <sec:authorize access="hasRole('ADMIN')">
                    <form method="post">
                        <div class="row g-1">
                            <label class="form-label">Status</label>
                            <div class="col-sm-8">
                                <select class="form-select" id="delivery" name="statusType">
                                    <c:forEach items="${status}" var="status">
                                        <option value="${status}" ${order.status == status ? 'selected' : ''}>${status.title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <button type="submit" class="form-control btn btn-outline-dark">Update</button>
                            </div>
                        </div>
                    </form>
                </sec:authorize>

                <sec:authorize access="hasRole('USER')">
                    <label class="form-label">Status</label>
                    <div class="col-sm-6">

                        <c:url value="/user/order/cancel" var="urlCancel">
                            <c:param name="id" value="${order.id}"/>
                        </c:url>
                        <a href="${urlCancel}"
                           class="form-control btn btn-outline-primary ${order.status.name()=='COMPLETED' ? 'disabled' : ''}
                                                                        ${order.status.name()=='CANCELED' ? 'disabled' : ''}">
                            Cancel
                        </a>
                    </div>
                    <div class="col-sm-6">
                        <c:url value="/user/order/repeat" var="urlRepeat">
                            <c:param name="id" value="${order.id}"/>
                        </c:url>
                        <a href="${urlRepeat}" class="form-control btn btn-outline-primary">Repeat order</a>
                        <div class="row">
                            <small class="text-primary text-center">Your cart will be emptied</small>
                        </div>
                    </div>
                </sec:authorize>

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
                <sec:authorize access="hasRole('ADMIN')">
                    <th scope="col">In Stock</th>
                </sec:authorize>
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
                    <sec:authorize access="hasRole('ADMIN')">
                        <td>${product.product.quantity}</td>
                    </sec:authorize>
                    <td>${product.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
