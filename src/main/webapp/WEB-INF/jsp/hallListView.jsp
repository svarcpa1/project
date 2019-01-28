<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Firma - seznam hal</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="../css/pricing.css" rel="stylesheet">

</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal"><a href="./">Firma</a></h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <%@include file="_menu.jsp" %>
    </nav>
</div>

<c:if test="${successMsg!=null}">
    <div class="alert alert-warning" role="alert">
        <strong>Gratuluji!</strong> ${successMsg}
    </div>
</c:if>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Název pobočky</h1>
    <p class="lead">Přehled hal</p>
</div>

<div class="container">
    <div class="card-deck mb-3 text-center">

        <c:forEach items="${haly}" var="hala">

            <div class="card mb-4 shadow-sm">

                <div class="card-header bg-primary text-white">
                    <h4 class="my-0 font-weight-normal">${hala.name} (${hala.id})</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>${hala.description}</li>

                    </ul>
                    <a href="hall/${hala.id}"><button type="button" class="btn btn-lg btn-block btn-outline-primary">
                        Detail haly</button>
                    </a>

                    <security:authorize access="hasRole('ROLE_Admin')">
                        <a href="deleteHall/${hala.id}">
                            <button type="button" class="btn btn-lg btn-block btn-outline-danger">Smazat halu</button>
                        </a>
                    </security:authorize>

                </div>

            </div>

        </c:forEach>

    </div>

</div>
</body>
</html>

