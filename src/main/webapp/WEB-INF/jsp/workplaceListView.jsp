<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Firma - detail haly - ${hala.name}</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../css/pricing.css" rel="stylesheet">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <%@include file="_menu.jsp" %>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Hala: ${hala.name} (${hala.id})</h1>
    <p class="lead">Přehled pracovišť</p>
</div>

<div class="container">

    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-primary text-white">
                <h4 class="my-0 font-weight-normal">${hala.name} (${hala.id})</h4>
            </div>
            <div class="card-body">

                <ul class="list-unstyled mt-3 mb-4">
                    <li>${hala.description}</li>
                </ul>

                <security:authorize access="hasRole('ROLE_Admin')">
                    <a href="/hall/edit/${hala.id}">
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">
                            Editovat popis haly</button>
                    </a>
                </security:authorize>

            </div>
        </div>
    </div>

    <div class="card-deck mb-3 text-center">
        <c:forEach items="${hala.workplaces}" var="workplace">
            <div class="card mb-2 shadow-sm">
                <div class="card-header bg-success text-white">
                    <h4 class="my-0 font-weight-normal">${workplace.name} (${workplace.id})</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>${workplace.description}</li>

                    </ul>
                    <a href="${workplace.hall.id}/workplace/${workplace.id}">
                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Detail pracoviště
                        </button>
                    </a>

                    <security:authorize access="hasRole('ROLE_Admin')">
                        <a href="${workplace.hall.id}/deleteWorkplace/${workplace.id}">
                            <button type="button" class="btn btn-lg btn-block btn-outline-danger">
                                Smazat pracoviště</button>
                        </a>
                    </security:authorize>

                </div>
            </div>
        </c:forEach>
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

