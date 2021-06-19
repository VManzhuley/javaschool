<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="p-3 bg-white col-lg-2">
    <ul class="list-unstyled ps-0">
        <c:forEach var="category" items="${categoriesForSidebar}">
            <c:if test="${not empty category.categoriesChild}">
                <li class="mb-1">
                    <button class="btn" data-bs-toggle="collapse"
                            data-bs-target="#${fn:replace(category.name," ","")}-collapse">
                            ${category.name}
                    </button>

                    <div class="collapse mx-lg-3" id="${fn:replace(category.name," ","")}-collapse">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <c:forEach var="categoryChild" items="${category.categoriesChild}">
                                <li>
                                    <button class="btn btn-sm" data-bs-toggle="collapse"
                                            data-bs-target="#${fn:replace(categoryChild.name," ","")}-collapse">
                                            ${categoryChild.name}
                                    </button>

                                    <div class="collapse"
                                         id="${fn:replace(categoryChild.name," ","")}-collapse">
                                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                            <c:forEach var="productAbs" items="${categoryChild.productsAbs}">
                                                <li><a href="/admin/products/${productAbs.id}" class="link-dark rounded">${productAbs.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:if>
            <c:if test="${empty category.categoriesChild}">
                <li class="mb-1">

                    <button class="btn" data-bs-toggle="collapse"
                            data-bs-target="#${fn:replace(category.name," ","")}-collapse">
                            ${category.name}
                    </button>

                    <div class="collapse" id="${fn:replace(category.name," ","")}-collapse">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <c:forEach var="productAbs" items="${category.productsAbs}">
                                <li><a href="/admin/products/${productAbs.id}" class="link-dark rounded">${productAbs.name}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:if>

        </c:forEach>
        <li class="border-top my-3"></li>
    </ul>

    <a href="/admin/products/add" class="btn btn-outline-dark w-100">Add new product</a>
    <a href="/admin/category" class="btn btn-outline-dark w-100 my-3">Manage category</a>

</div>
