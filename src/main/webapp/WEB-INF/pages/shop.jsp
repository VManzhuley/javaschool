<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>
    <div class="row">
        <div class="col-md-3">
            <div class="p-3 bg-white" style="width: 280px;">
                <ul class="list-unstyled ps-0">
                    <c:forEach var="category" items="${categoryList}">
                        <c:if test="${not empty category.categoriesChild}">
                            <li class="mb-1">
                                <button class="btn"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#${fn:replace(category.name," ","")}-collapse"
                                        aria-expanded="false">
                                        ${category.name}
                                </button>

                                <div class="collapse" id="${fn:replace(category.name," ","")}-collapse">
                                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                        <c:forEach var="categoryChild" items="${category.categoriesChild}">
                                            <li>
                                                <c:url value="/shop" var="url">
                                                    <c:param name="category" value="${categoryChild.idCategory}"/>
                                                </c:url>
                                                <a href="${url}" class="link-dark rounded">${categoryChild.name}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${empty category.categoriesChild}">
                            <c:url value="/shop" var="url">
                                <c:param name="category" value="${category.idCategory}"/>
                            </c:url>
                            <li class="mb-1">
                                <a href="${url}" class="btn" aria-expanded="false">
                                        ${category.name}
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <hr/>


            <p class="h5 my-1">Description</p>
            <div class="card card-body mb-3">
                <c:forEach var="description" items="${descriptions}">
                    <div class="form-check">
                        <c:url value="/shop" var="url">
                            <c:forEach items="${param}" var="entry">
                                <c:if test="${entry.key != 'description'}">
                                    <c:param name="${entry.key}" value="${entry.value}"/>
                                </c:if>
                            </c:forEach>
                            <c:param name="description" value="${description.id}"/>
                        </c:url>
                        <input class="form-check-input" type="radio" name="description"
                               value="${url}" ${param.get("description")==description.id?'checked':''}
                               onclick="location=this.value">
                        <label class="form-check-label">
                                ${description.name}
                        </label>
                    </div>
                </c:forEach>
            </div>

            <p class="h5 my-1">Composition</p>

            <div class="card card-body mb-3">
                <c:forEach var="composition" items="${compositions}">
                    <div class="form-check">
                        <c:url value="/shop" var="url">
                            <c:forEach items="${param}" var="entry">
                                <c:if test="${entry.key != 'composition'}">
                                    <c:param name="${entry.key}" value="${entry.value}"/>
                                </c:if>
                            </c:forEach>
                            <c:param name="composition" value="${composition.id}"/>
                        </c:url>
                        <input class="form-check-input" type="radio" name="composition"
                               id="composition${composition.id}"
                               value="${url}" ${param.get("composition")==composition.id?'checked':''}
                               onclick="location=this.value">
                        <label class="form-check-label" for="composition${composition.id}">
                                ${composition.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
            <c:url value="/shop" var="url">
                <c:forEach items="${param}" var="entry">
                    <c:if test="${entry.key != 'composition'}">
                        <c:if test="${entry.key != 'description'}">
                            <c:param name="${entry.key}" value="${entry.value}"/>
                        </c:if>
                    </c:if>

                </c:forEach>
            </c:url>
            <a href="${url}" class="w-100 btn btn-primary my-3">Reset filers</a>

        </div>


        <div class="col-md-9">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <select class="form-select" onchange="location = this.options[this.selectedIndex].value;" >
                    <option selected disabled hidden>Choose sorting type</option>
                    <c:url value="/shop" var="url">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'sort'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                    </c:url>
                    <option value="${url}">Default</option>
                    <c:url value="/shop" var="url">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'sort'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                        <c:param name="sort" value="name"/>
                    </c:url>
                    <option value="${url}" ${param.get("sort")=='name' ? 'selected' : ''}>Name</option>
                    <c:url value="/shop" var="url">
                        <c:forEach items="${param}" var="entry">
                            <c:if test="${entry.key != 'sort'}">
                                <c:param name="${entry.key}" value="${entry.value}"/>
                            </c:if>
                        </c:forEach>
                        <c:param name="sort" value="price"/>
                    </c:url>
                    <option value="${url}" ${param.get("sort")=='price' ? 'selected' : ''}>Price</option>
                </select>
                <c:forEach var="productAbs" items="${productAbsList}">
                    <div class="col">
                        <div class="card h-100">
                            <img src="${productAbs.photoLink}" class="rounded mx-auto d-block"
                                 style="width: 18rem;" alt="${productAbs.name}">
                            <div class="card-body">
                                <c:url value="/product" var="url">
                                    <c:param name="id" value="${productAbs.id}"/>
                                </c:url>
                                <h5 class="card-title"><a href="${url}">${productAbs.name}</a></h5>
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



