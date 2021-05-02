<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="p-3 bg-white" style="width: 280px;">
    <ul class="list-unstyled ps-0">
        <c:forEach var="category" items="${categoryList}">
            <c:if test="${category.categoryParent==null}">
                <li class="mb-1">
                    <button class="btn btn-toggle align-items-center rounded collapsed"
                            data-bs-toggle="collapse"
                            data-bs-target="#${category.name}-collapse" aria-expanded="false">
                            ${category.name}
                    </button>

                    <div class="collapse" id="${category.name}-collapse">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <c:forEach var="subCategory" items="${categoryList}">
                                <c:if test="${category.name==subCategory.categoryParent.name}">
                                    <li><a href="/category/${subCategory.id}" class="link-dark rounded">${subCategory.name}</a></li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>
