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
</head>
<body>
<div class="container">

    <jsp:include page="header.jsp"/>


    <form method="post">
        <div class="row g-5 justify-content-center">

            <div class="col-md-6 text-center py-3 mb-4">


                <p class="h2">Step 1: Choose category or create new</p>
                <div class="input-group mb-3">
                    <select class="form-select" onchange="this.form.submit()" name="step1">
                        <option value='0'>---</option>
                        <c:forEach var="category" items="${listStep1}">
                            <option value="${category.idCategory}" ${step1==category.idCategory ? 'selected' : ''}>
                                    ${category.name}</option>
                            <c:set var="withoutParent" value="${category.idCategory}"/>
                        </c:forEach>
                    </select>
                    <input type="text" class="form-control" name="categoryNew">

                    <input type="submit" class="btn btn-sm btn-primary" name="new" value="New"/>

                </div>
                <c:if test="${not empty message}">
                    <div class="text-center"><p class="text-danger">${message}</p></div>
                </c:if>
                <hr/>
                <p class="h2">Step 2: Choose product</p>
                <div class="input-group mb-3">
                    <select class="form-select" name="step2">
                        <option value="0" ${empty listStep2 ? '' : 'disabled'}>---</option>
                        <c:forEach var="productAbs" items="${listStep2}">
                            <option value="${productAbs.id}">${productAbs.name}</option>

                        </c:forEach>
                    </select>
                </div>
                <hr/>
                <p class="h2">Step 3: Choose new parent</p>
                <div class="input-group mb-3">
                    <select class="form-select" name="step3" onchange="this.form.submit()">
                        <option value="0">---</option>
                        <c:forEach var="category" items="${listStep3}">
                            <option value="${category.idCategory}">${category.name}</option>

                        </c:forEach>
                        <c:if test="${empty listStep2}">
                            <option value="${withoutParent+1}">Without Parent</option>
                        </c:if>
                    </select>
                </div>
            </div>


        </div>
    </form>


</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
