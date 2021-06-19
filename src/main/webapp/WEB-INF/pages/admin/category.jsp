<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 08.05.2021
  Time: 16:32
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
    <div class="row">
        <jsp:include page="sidebarProducs.jsp"/>

        <main class="col-md-9 ms-sm-auto col-lg-3 px-md-4 p-3">
            <form method="post">
                <p class="h3 my-3">Select category</p>
                <p class="h6">You can choose only category without child</p>

                    <select class="form-select" name="category">

                        <c:forEach var="category" items="${categories}">
                            <option value="${category.idCategory}">
                                    ${category.fullName}</option>
                            <c:set var="withoutParent" value="${category.idCategory}"/>
                        </c:forEach>
                    </select>

                <p class="h3 my-3">Select new parent category</p>
                <p class="h6">You can choose only category without parent or products</p>

                    <select class="form-select" name="newParentCategory">

                        <c:forEach var="categoryParent" items="${categoriesParent}">
                            <option value="${categoryParent.idCategory}">
                                    ${categoryParent.fullName}</option>
                        </c:forEach>
                        <option value="0">Without Parent</option>
                    </select>

                <input type="submit" class="w-100 btn btn-dark my-2" value="Confirm" name="change">
                <hr/>
                <p class="h3">Create new</p>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="categoryNew">
                    <input type="submit" class="btn btn-sm btn-dark" name="new" value="New"/>
                </div>

            </form>
        </main>
        <div class="col-lg-7"></div>


    </div>
</div>

</body>
</html>
