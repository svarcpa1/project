<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Firma - detail pracoviště - ${workplace.name}</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../css/pricing.css" rel="stylesheet">
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal"><a href="../../../">Firma</a></h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="../../../addHall">Přidat halu</a>
        <a class="p-2 text-dark" href="../../../addWorkplace">Přidat pracoviště</a>
        <a class="p-2 text-dark" href="../../../addDocument">Přidat dokumentaci</a>
        <a class="p-2 text-dark" href="../../../addWorker">Přidat zaměstnance</a>
    </nav>
    <a class="btn btn-outline-primary" href="#">Přihlásit se</a>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Pracoviště: ${workplace.name} (${workplace.id})</h1>
    <p class="lead">Přehled zaměstanců a dokumentace</p>
</div>

<div class="container">

    <div class="card-deck mb-3 text-center">
        <div class="card mb-4 shadow-sm">
            <div class="card-header bg-success text-white">
                <h4 class="my-0 font-weight-normal">${workplace.name} (${workplace.id})</h4>
            </div>
            <div class="card-body">
                <ul class="list-unstyled mt-3 mb-4">
                    <li>${workplace.description}</li>

                </ul>
                <a href="/hall/edit/${workplace.id}">
                    <button type="button" class="btn btn-lg btn-block btn-outline-primary">Editovat popis pracoviště</button>
                </a>
            </div>
        </div>
    </div>

    <div class="card-deck mb-3 text-center">
        <c:forEach items="${workplace.workers}" var="worker">
            <div class="card mb-2 shadow-sm">
                <div class="card-header bg-warning text-white">
                    <h4 class="my-0 font-weight-normal">${worker.firstName} ${worker.surName} (${worker.id})</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>${worker.role.name}</li>

                    </ul>
                    <a style="display: none;" href="/worker/${worker.id}"><button
                            type="button"
                            class="btn btn-lg btn-block btn-outline-primary">Detail zaměstance</button></a>
                    <a href="/deleteWorker/${worker.id}"><button
                            type="button"
                            class="btn btn-lg btn-block btn-outline-danger">Smazat zaměstnance</button></a>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="card-deck mb-3 text-center">
        <c:forEach items="${workplace.documents}" var="document">
            <div class="card mb-1 shadow-sm">
                <div class="card-header bg-danger text-white">
                    <h4 class="my-0 font-weight-normal">${document.name} (${document.id})</h4>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>Vytvořil: ${document.workerCreated.firstName} ${document.workerCreated.surName}</li>
                        <li>Datum platnosti od: ${document.dateCreated}</li>
                        <li>Datum platnosti do: ${document.dateExpired}</li>
                    </ul>
                    <a href="/loadDocument/${document.id}"><button
                            type="button"
                            class="btn btn-lg btn-block btn-outline-primary">Detail dokumentace</button></a>
                    <a href="/deleteDocument/${document.id}"><button
                            type="button"
                            class="btn btn-lg btn-block btn-outline-danger">Smazat dokumentaci</button></a>
                </div>
            </div>
        </c:forEach>
    </div>

    <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">

            <div class="col-6 col-md">
                <h5>Pracovníci:</h5>
                <ul class="list-unstyled text-small">
                    <c:forEach items="${workplace.workers}" var="workers">
                        <li><a class="text-muted" href="#">${workers.firstName} ${workers.surName}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-6 col-md">
                <h5>Dokumentace:</h5>
                <ul class="list-unstyled text-small">
                    <c:forEach items="${workplace.documents}" var="documents">
                        <li><a class="text-muted" href="#">${documents.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </footer>
</div>
</body>
</html>

