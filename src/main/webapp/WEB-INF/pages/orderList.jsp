<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 09.05.2021
  Time: 18:31
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
    <table class="table">
        <thead>
        <tr >
            <th scope="col">#</th>
            <th scope="col">Date</th>
            <th scope="col">Client</th>
            <th scope="col">Total Amount</th>
            <th scope="col">Status</th>
            <th scope="col">View order</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orderList}">
            <c:choose>
                <c:when test="${order.status=='Waiting for payment'}">
                    <c:set var="var" value="table-warning"/>
                </c:when>
                <c:when test="${order.status=='Transferred to TC'}">
                    <c:set var="var" value="table-info"/>
                </c:when>
                <c:when test="${order.status=='Completed'}">
                    <c:set var="var" value="table-success"/>
                </c:when>
                <c:when test="${order.status=='Canceled'}">
                    <c:set var="var" value="table-danger"/>
                </c:when>
                <c:otherwise>
                    <c:set var="var" value=""/>
                </c:otherwise>
            </c:choose>
            <tr class="${var}">
                <th scope="row">${order.id}</th>
                <td>${order.date}</td>
                <td>${order.client.lastname} ${order.client.name}</td>
                <td>${order.amountTotal}</td>
                <td>${order.status}</td>
                <c:url value="/admin/order" var="url">
                    <c:param name="id" value="${order.id}"/>
                </c:url>
                <td><a href="${url}" class="btn btn-outline-primary">Go</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <nav class="py-3" aria-label="Page navigation example">
        <ul class="pagination">

            <c:if test="${totalPages > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>

                <c:url value="/admin/orders" var="url">
                    <c:param name="page" value="1"/>
                </c:url>
                <li class="page-item ${page == 1 ? disabled : active}">
                    <a class="page-link" href="${url}"><<<</a>
                </li>

                <c:url value="/admin/orders" var="url">
                    <c:param name="page" value="${page - 1}"/>
                </c:url>
                <li class="page-item ${page == 1 ? disabled : active}">
                    <a class="page-link" href="${url}"><</a>
                </li>

                <c:if test="${totalPages <= 5}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="${totalPages}"/>
                </c:if>

                <c:if test="${totalPages > 5}">
                    <c:choose>
                        <c:when test="${page < 3}">
                            <c:set var="begin" value="1"/>
                            <c:set var="end" value="5"/>
                        </c:when>
                        <c:when test="${page > totalPages - 2}">
                            <c:set var="begin" value="${totalPages - 4}"/>
                            <c:set var="end" value="${totalPages}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="begin" value="${page - 2}"/>
                            <c:set var="end" value="${page + 2}"/>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:forEach begin="${begin}" end="${end}" step="1" varStatus="i">
                    <c:url value="/admin/orders" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <c:set value="active" var="current"/>
                    <c:set value="" var="perspective"/>
                    <li class="page-item ${page == i.index ? current : perspective}"><a class="page-link" href="${url}">${i.index}</a></li>
                </c:forEach>

                <c:url value="/admin/orders" var="url">
                    <c:param name="page" value="${page + 1}"/>
                </c:url>
                <li class="page-item ${page == totalPages ? disabled : active}">
                    <a class="page-link" href="${url}">></a>
                </li>

                <c:url value="/admin/orders" var="url">
                    <c:param name="page" value="${totalPages}"/>
                </c:url>
                <li class="page-item ${page == totalPages ? disabled : active}">
                    <a class="page-link" href="${url}">>>></a>
                </li>



            </c:if>
        </ul>
    </nav>


</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
