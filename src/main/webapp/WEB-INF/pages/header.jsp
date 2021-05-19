<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 border-bottom">
    <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32">
            <use xlink:href=""></use>
        </svg>
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">Delivery Terms </a></li>
        <li><a href="#" class="nav-link px-2 link-dark">Payment methods</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">FAQs</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">Contacts</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <sec:authorize access="!isAuthenticated()">
            <a href="/registration" class="btn btn-outline-primary"><span>Registration</span>
            </a>
            <a href="/login" class="btn btn-primary me-2">
                <span>Login</span>
            </a>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">

            <a href="/logout" class="btn btn-outline-primary me-2">
                <span>Logout</span>
            </a>
        </sec:authorize>


        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Cart
        </button>
    </div>
</header>
<sec:authorize access="hasRole('ADMIN')">
    <div class="container d-flex flex-wrap justify-content-center bg-light">
        <ul class="nav nav">
            <li><a href="/admin/orders" class="nav-link px-2 link-dark">Orders</a></li>
            <li><a href="/admin/statistic" class="nav-link px-2 link-dark">Statistic</a></li>
            <li><a href="/admin/product-add" class="nav-link px-2 link-secondary">Add product</a></li>
            <li><a href="/admin/category" class="nav-link px-2 link-secondary">Category</a></li>
        </ul>
    </div>
</sec:authorize>

<sec:authorize access="hasRole('USER')">
    <div class="container d-flex flex-wrap justify-content-center bg-light">
        <ul class="nav nav">
            <li><a href="/user/account" class="nav-link px-2 link-dark">Account</a></li>
            <li><a href="/user/orders" class="nav-link px-2 link-dark">Orders</a></li>
        </ul>
    </div>
</sec:authorize>

<div class="border-bottom mb-3"></div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Your cart</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <c:if test="${not empty cart.cartItems}">
                    <c:forEach var="cartItem" items="${cart.cartItems}">
                        <div class="card mb-3 card-link">
                            <div class="row g-0">
                                <div class="col-md-4 d-flex align-items-center">
                                    <img src="${cartItem.product.colour.photoLink}" alt="..."
                                         class="img-fluid">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <c:url value="/product" var="url">
                                            <c:param name="id" value="${cartItem.product.productAbs.id}"/>
                                        </c:url>
                                        <h3 class="card-title"><a href="${url}">${cartItem.product.productAbs.name}</a>
                                        </h3>
                                        <p class="h6"><small class="text-muted">${cartItem.product.article}</small></p>
                                        <p class="h6">
                                            <small>Colour: ${cartItem.product.colour.name}<br>Size: ${cartItem.product.size.size}<br>Qty: ${cartItem.quantity}
                                            </small></p>
                                        <p class="h4"> ${cartItem.amount} RUB</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <p class="h2">Total amount: ${cart.amountTotal} RUB</p>
                </c:if>
                <c:if test="${empty cart.cartItems}">
                    <h3 class="card-title">It's empty here</h3>
                </c:if>
            </div>
            <div class="modal-footer">
                <c:if test="${not empty cart.cartItems}">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Back to shopping!</button>

                    <a href="/cart" class="btn btn-primary">
                        <span>Order these products!</span>
                    </a>

                </c:if>
                <c:if test="${empty cart.cartItems}">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Let's try to buy something!
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>
