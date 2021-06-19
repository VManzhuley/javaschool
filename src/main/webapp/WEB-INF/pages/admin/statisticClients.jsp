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
    <link href="/assets/css/dashboard.css" rel="stylesheet">
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
</head>
<body>
<jsp:include page="headerAdmin.jsp"/>
<div class="container-fluid">

    <div class="row g-5">

        <div class="col-md-5 col-lg-2 py-3 mx-3">

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
                            <input class="form-check-input" type="radio" name="status" value="${status.name()}" ${param.get("status")==status.name() ? 'checked' : ''}>
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
                        <input class="form-check-input" type="radio" name="sort" value="average" ${param.get("sort")=='average' ? 'checked' : ''}>
                        <label class="form-check-label">
                            Average order
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="sort" value="count" ${param.get("sort")=='count' ? 'checked' : ''}>
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
                        <input class="form-check-input" type="radio" name="pageSize" value="20" ${param.get("pageSize")==20 ? 'checked' : ''}>
                        <label class="form-check-label">
                            20
                        </label>
                    </div>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="pageSize" value="0" ${param.get("pageSize")==0 ? 'checked' : ''}>
                        <label class="form-check-label">
                            All
                        </label>
                    </div>
                </div>

                <button type="submit" class="w-100 btn btn-dark">Confirm</button>

            </form>
        </div>


        <div class="col-md-5 col-lg-9 py-3">

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

</body>
</html>

