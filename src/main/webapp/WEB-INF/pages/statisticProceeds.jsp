<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 16.05.2021
  Time: 14:52
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

    <div class="row g-5">

        <div class="col-md-5 col-lg-3 py-5">

            <form method="post">
                <p class="h5">Period</p>
                <div class="mb-5">
                    <div class="form-check">

                        <input class="form-check-input" type="radio" name="period" value="month" checked>
                        <p class="h6"> By month </p>
                        <input class="form-control" type="month" name="month" value="${pMonth}">
                        <div class="mb-3"></div>

                        <input class="form-check-input" type="radio" name="period"
                               value="week" ${pPeriod=='week' ? 'checked' : ''}>
                        <p class="h6"> By week </p>
                        <input class="form-control" type="week" name="week" value="${pWeek}">
                        <div class="mb-3"></div>

                        <input class="form-check-input" type="radio" name="period"
                               value="custom" ${pPeriod=='custom' ? 'checked' : ''}>
                        <p class="h6"> Custom </p>


                        <label class="form-check-label"> from </label>

                        <input class="form-control" type="date" name="dateFrom" value="${pDateFrom}">

                        <label class="form-check-label"> to </label>

                        <input class="form-control" type="date" name="dateTo" value="${pDateTo}">
                    </div>


                </div>
                <button type="submit" class="btn btn-primary">Confirm</button>


            </form>
        </div>


        <div class="col-md-5 col-lg-9">
            <h4 class="mb-3 text-center">Orders</h4>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Date</th>
                    <th scope="col">Client</th>
                    <th scope="col">Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.date}</td>
                        <td>${order.client.lastname} ${order.client.name}</td>
                        <td>${order.amount}</td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                <td></td>
                <td></td>
                <td>Total:</td>
                <td>${proceeds}</td>
                </tfoot>
            </table>
        </div>


    </div>

</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>

