<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="p-3 bg-white" style="width: 280px;">
    <ul class="list-unstyled ps-0">
        <c:forEach var="category" items="${categoryList}">
            <c:if test="${category.categoryParent==null}">
                <c:if test="${not empty category.categoriesChild}">
                    <li class="mb-1">
                        <button class="btn btn-toggle align-items-center rounded collapsed"
                                data-bs-toggle="collapse"
                                data-bs-target="#${category.name}-collapse" aria-expanded="false">
                                ${category.name}
                        </button>

                        <div class="collapse" id="${category.name}-collapse">
                            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                                <c:forEach var="categoryChild" items="${category.categoriesChild}">
                                    <li>
                                        <c:url value="/shop" var="url">
                                            <c:param name="category" value="${categoryChild.id}"/>
                                        </c:url>
                                        <a href="${url}" class="link-dark rounded">${categoryChild.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </li>
                </c:if>
                <c:if test="${empty category.categoriesChild}">

                    <form action="/shop">
                        <input type="hidden" name="category" value="${category.id}"/>
                        <button class="btn btn-toggle align-items-center rounded collapsed" aria-expanded="false">
                                ${category.name}
                        </button>
                    </form>

                </c:if>
            </c:if>
        </c:forEach>
    </ul>
</div>
