<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap"></use>
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
        <button type="button" class="btn btn-outline-primary">Login</button>
        <button type="button" class="btn btn-primary me-2">Sign-up</button>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
            Cart
        </button>
    </div>
</header>


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
                        <div class="card mb-3">
                            <div class="row g-0">
                                <div class="col-md-4 d-flex align-items-center">
                                    <img src="../assets/img/product${cartItem.product.colour.photoLink}" alt="..."
                                         class="img-fluid">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h3 class="card-title">${cartItem.product.productAbs.name}</h3>
                                        <p class="h6"><small class="text-muted">${cartItem.product.article}</small></p>
                                        <p class="h6">
                                            <small>Colour: ${cartItem.product.colour.name}<br>Size: ${cartItem.product.size}<br>Qta: ${cartItem.quantity}
                                            </small></p>
                                        <p class="h4"> ${cartItem.amount} rubles</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <p class="h2">Total amount: ${cart.amountTotal} rubles</p>
                </c:if>
                <c:if test="${empty cart.cartItems}">
                    <h3 class="card-title">It's empty here</h3>
                </c:if>
            </div>
            <div class="modal-footer">
                <c:if test="${not empty cart.cartItems}">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Back to shopping!</button>
                    <button type="button" class="btn btn-primary">Order these products!</button>
                </c:if>
                <c:if test="${empty cart.cartItems}">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Let's try to buy something!
                    </button>
                </c:if>
            </div>
        </div>
    </div>
</div>