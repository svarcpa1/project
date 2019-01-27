<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Access denied</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../css/pricing.css" rel="stylesheet">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal"><a href="../">Firma</a></h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <%@include file="_menu.jsp" %>
    </nav>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Access denied</h1>
</div>

<div class="container">

    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <c:if test="${message != null}">
                        <h3 style="color: red;">${message}</h3>
                    </c:if>
                    <c:if test="${userInfo != null}">
                        <div>${userInfo}</div>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>

    <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
            <div class="col-6 col-md">
                <h5>Pracoviště:</h5>
                <ul class="list-unstyled text-small">
                    <c:forEach items="${hala.workplaces}" var="workplace">
                        <li><a class="text-muted" href="#">${workplace.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </footer>
</div>
</body>
</html>

