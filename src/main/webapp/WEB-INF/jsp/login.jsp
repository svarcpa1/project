<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <title>Firma: přihlášení</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/signin.css" rel="stylesheet">
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="text-center">
    <form class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal">Prosím přihlašte se</h1>
        <label for="login" class="sr-only">Login</label>
        <input type="text" id="login" class="form-control" placeholder="Login" required autofocus>
        <label for="inputPassword" class="sr-only">Heslo</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Heslo" required>
        <button name="action" class="btn btn-lg btn-success btn-block" type="submit">Příhlásit se</button>
        <button name="action" class="btn btn-lg btn-danger btn-block" value="cancel" type="submit">Zpět</button>
    </form>
</body>
</html>
