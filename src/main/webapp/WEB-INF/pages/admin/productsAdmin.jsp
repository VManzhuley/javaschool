<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>GIVOVA-RUS</title>
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

        <main class="col-md-9 ms-sm-auto col-lg-8 px-md-4">

        </main>
    </div>
</div>

</body>
</html>
