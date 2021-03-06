<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Date</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th scope="col">Client</th>
                </sec:authorize>
                <th scope="col">Total Amount</th>
                <th scope="col">Status</th>
                <th scope="col">View order</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}">
                <c:choose>
                    <c:when test="${order.status.title=='Waiting for payment'}">
                        <c:set var="var" value="table-warning"/>
                    </c:when>
                    <c:when test="${order.status.title=='Transferred to TC'}">
                        <c:set var="var" value="table-info"/>
                    </c:when>
                    <c:when test="${order.status.title=='Completed'}">
                        <c:set var="var" value="table-success"/>
                    </c:when>
                    <c:when test="${order.status.title=='Canceled'}">
                        <c:set var="var" value="table-danger"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="var" value=""/>
                    </c:otherwise>
                </c:choose>
                <tr class="${var}">
                    <th scope="row">${order.id}</th>
                    <td>${order.date}</td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td>${order.client.lastname} ${order.client.name}</td>
                    </sec:authorize>
                    <td>${order.amount}</td>
                    <td>${order.status.title}</td>
                    <sec:authorize access="hasRole('USER')">
                        <c:set var="urlsec" value="/user/order"/>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <c:set var="urlsec" value="/admin/order"/>
                    </sec:authorize>
                    <c:url value="${urlsec}" var="url">
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

                    <c:url value="${urlsec}s" var="url">
                        <c:param name="page" value="1"/>
                    </c:url>
                    <li class="page-item ${page == 1 ? disabled : active}">
                        <a class="page-link" href="${url}"><<<</a>
                    </li>

                    <c:url value="${urlsec}s" var="url">
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
                        <c:url value="${urlsec}s" var="url">
                            <c:param name="page" value="${i.index}"/>
                        </c:url>
                        <c:set value="active" var="current"/>
                        <c:set value="" var="perspective"/>
                        <li class="page-item ${page == i.index ? active : perspective}"><a class="page-link"
                                                                                            href="${url}">${i.index}</a>
                        </li>
                    </c:forEach>

                    <c:url value="${urlsec}s" var="url">
                        <c:param name="page" value="${page + 1}"/>
                    </c:url>
                    <li class="page-item ${page == totalPages ? disabled : active}">
                        <a class="page-link" href="${url}">></a>
                    </li>

                    <c:url value="${urlsec}s" var="url">
                        <c:param name="page" value="${totalPages}"/>
                    </c:url>
                    <li class="page-item ${page == totalPages ? disabled : active}">
                        <a class="page-link" href="${url}">>>></a>
                    </li>


                </c:if>
            </ul>
        </nav>


    </div>

</body>
</html>
