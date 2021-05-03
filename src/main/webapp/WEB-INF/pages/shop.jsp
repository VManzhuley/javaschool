<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp"/>
        </div>


        <div class="col-md-9">
            <div class="row row-cols-1 row-cols-md-3 g-4">


                <select class="form-select" onchange="location = this.options[this.selectedIndex].value;">
                    <option selected>CHOOSE SORTING TYPE</option>
                    <c:url value="/shop" var="url">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'sort'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                        <c:param name="sort" value="name"/>
                    </c:url>
                    <option value="${url}">NAME</option>
                    <c:url value="/shop" var="url">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'sort'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                        <c:param name="sort" value="price"/>
                    </c:url>
                    <option value="${url}">PRICE</option>
                </select>
                <c:forEach var="productAbs" items="${productAbsList}">
                    <div class="col">
                        <div class="card h-100">
                            <img src="/assets/img/product${productAbs.photoLink}" class="rounded mx-auto d-block"
                                 style="width: 18rem;">
                            <div class="card-body">
                                <c:url value="/product" var="url">
                                     <c:param name="id" value="${productAbs.id}"/>
                                </c:url>
                                <h5 class="card-title"><a href="${url}">${productAbs.name}</a>
                                </h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${productAbs.article}</li>
                                    <li class="list-group-item">${productAbs.price}</li>
                                    <li class="list-group-item">${productAbs.description}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${totalPages>1}">
                <nav class="py-3" aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${totalPages}" step="1" varStatus="i">
                            <c:url value="/shop" var="url">
                                <c:forEach items="${param}" var="entry">
                                    <c:if test="${entry.key != 'page'}">
                                        <c:param name="${entry.key}" value="${entry.value}"/>
                                    </c:if>
                                </c:forEach>
                                <c:param name="page" value="${i.index}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${url}">${i.index}</a></li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>

        </div>
    </div>
</div>

<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>



