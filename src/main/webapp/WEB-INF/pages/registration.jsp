<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: GIVOVA
  Date: 14.05.2021
  Time: 14:24
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
        <div class="row g-1 justify-content-center">
            <div class="col-md-6">
                <div class="row g-3">
                    <div class="col-md-4 col-lg-6">
                        <div class="row g-1">
                            <h4 class="mb-0">Details</h4>
                            <small class="text-muted">required</small>
                            <div class="col-md-12">
                                <label class="form-label">Name</label>
                                <spring:bind path="client.name">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.name}">
                                </spring:bind>
                                <spring:hasBindErrors name="client">
                                    <c:forEach var="error" items="${errors.getFieldErrors('name')}">
                                        <div class="row">
                                            <small class="text-danger"><spring:message message="${error}"/></small>
                                        </div>
                                    </c:forEach>
                                </spring:hasBindErrors>


                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Lastname</label>

                                <spring:bind path="client.lastname">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.lastname}">
                                </spring:bind>
                                <spring:hasBindErrors name="client">
                                    <c:forEach var="error" items="${errors.getFieldErrors('lastname')}">
                                        <div class="row">
                                            <small class="text-danger"><spring:message message="${error}"/></small>
                                        </div>
                                    </c:forEach>
                                </spring:hasBindErrors>

                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Phone</label>
                                <div class="input-group has-validation">
                                    <spring:bind path="client.phone">
                                        <span class="input-group-text">+7</span>
                                        <input type="text" class="form-control" name="${status.expression}"
                                               value="${client.phone}">
                                    </spring:bind>

                                </div>
                                <spring:hasBindErrors name="client">
                                    <c:forEach var="error" items="${errors.getFieldErrors('phone')}">
                                        <div class="row">
                                            <small class="text-danger"><spring:message message="${error}"/></small>
                                        </div>
                                    </c:forEach>
                                </spring:hasBindErrors>

                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Email</label>
                                <div class="input-group has-validation">
                                    <spring:bind path="client.email">
                                        <span class="input-group-text">@</span>
                                        <input type="text" class="form-control" name="${status.expression}"
                                               value="${client.email}">
                                    </spring:bind>

                                </div>
                                <spring:hasBindErrors name="client">
                                    <c:forEach var="error" items="${errors.getFieldErrors('email')}">
                                        <div class="row">
                                            <small class="text-danger"><spring:message message="${error}"/></small>
                                        </div>
                                    </c:forEach>
                                </spring:hasBindErrors>
                                <c:if test="${not empty message}">
                                    <div class="row">
                                        <small class="text-danger">${message}</small>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Password</label>

                                <spring:bind path="client.password">
                                    <input type="password" class="form-control" name="${status.expression}">
                                </spring:bind>
                                <spring:hasBindErrors name="client">
                                    <c:forEach var="error" items="${errors.getFieldErrors('password')}">
                                        <div class="row">
                                            <small class="text-danger"><spring:message message="${error}"/></small>
                                        </div>
                                    </c:forEach>
                                </spring:hasBindErrors>

                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Repeat password</label>

                                <spring:bind path="client.matchingPassword">
                                    <input type="password" class="form-control" name="${status.expression}">
                                </spring:bind>
                                <spring:hasBindErrors name="client">
                                    <c:forEach var="error" items="${errors.globalErrors}">
                                    <div class="row">
                                        <small class="text-danger"><spring:message
                                                message="${error}"/></small>
                                    </div>
                                    </c:forEach>
                                </spring:hasBindErrors>

                            </div>

                        </div>
                    </div>

                    <div class="col-md-4 col-lg-6">
                        <div class="row g-1">
                            <h4 class="mb-0">Address</h4>
                            <small class="text-muted">optional</small>
                            <div class="col-md-12">
                                <label class="form-label">Zip</label>

                                <spring:bind path="client.zip">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.zip}">
                                </spring:bind>


                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Country</label>

                                <spring:bind path="client.country">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.country}">
                                </spring:bind>


                            </div>
                            <div class="col-md-12">
                                <label class="form-label">City</label>
                                <spring:bind path="client.city">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.city}">
                                </spring:bind>


                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Street</label>

                                <spring:bind path="client.street">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.street}">
                                </spring:bind>


                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Building</label>

                                <spring:bind path="client.building">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.building}">
                                </spring:bind>


                            </div>
                            <div class="col-md-12">
                                <label class="form-label">Apartment</label>

                                <spring:bind path="client.apartment">
                                    <input type="text" class="form-control" name="${status.expression}"
                                           value="${client.apartment}">
                                </spring:bind>


                            </div>
                        </div>

                    </div>
                    <hr class="my-4">

                </div>
                <button type="submit" class="w-100 btn btn-primary">Confirm</button>
            </div>
        </div>
    </form>
</div>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>
