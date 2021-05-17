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
                <p class="h5">Order status:</p>
                <div class="mb-5">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="status" value="ALL" checked>
                        <label class="form-check-label">
                            All
                        </label>
                    </div>
                    <c:forEach var="status" items="${status}">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="status" value="${status.name()}" ${pStatus==status.name() ? 'checked' : ''}>
                            <label class="form-check-label">
                                    ${status.title}
                            </label>
                        </div>
                    </c:forEach>

                </div>

                <p class="h5">Sort by:</p>
                <div class="mb-5">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" value="total"
                               checked>
                        <label class="form-check-label">
                            Total
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" value="average" ${pSort=='average' ? 'checked' : ''}>
                        <label class="form-check-label">
                            Average order
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" value="count" ${pSort=='count' ? 'checked' : ''}>
                        <label class="form-check-label">
                            Order count
                        </label>
                    </div>
                </div>

                <p class="h5">Show result:</p>
                <div class="mb-5">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pageSize" value="10"
                               checked>
                        <label class="form-check-label">
                            10
                        </label>
                    </div>


                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pageSize" value="20" ${pPageSize==20 ? 'checked' : ''}>
                        <label class="form-check-label">
                            20
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pageSize" value="0" ${pPageSize==0 ? 'checked' : ''}>
                        <label class="form-check-label">
                            All
                        </label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Confirm</button>

            </form>
        </div>


        <div class="col-md-5 col-lg-9">
            <h4 class="mb-3 text-center">Top</h4>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Client</th>
                    <th scope="col">Email</th>
                    <th scope="col">${pSort == 'average' ? 'Average order' : 'Total sum'}</th>
                    <th scope="col">Order count</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}" varStatus="i">
                <tr>
                    <th scope="row">${i.index+1}</th>
                    <td>${order.client.lastname} ${order.client.name}</td>
                    <td>${order.client.email}</td>
                    <td>${order.amount}</td>
                    <td>${order.id}</td>

                </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>


    </div>

</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>

