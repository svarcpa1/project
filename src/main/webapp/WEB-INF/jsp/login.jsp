<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

    <a href="/"><button name="action" class="btn btn-lg btn-danger btn-block" value="cancel" >Zpět</button></a>

    <form class="form-signin" action="../j_spring_security_check" method='POST'>
        <c:if test="${error== 'true'}">
            <div style="color:red;margin:10px 0px;">
                Login Failed<br />
                Reason :
                <c:if test="${pageContext.request.session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION') != null}">
                <span>
                        ${pageContext.request.session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION').message}
                 </span>
                </c:if>
            </div>
        </c:if>
        <h1 class="h3 mb-3 font-weight-normal">Prosím přihlašte se</h1>
        <label for="login" class="sr-only">Login</label>
        <input name="login" type="text" id="login" class="form-control" placeholder="Login" required autofocus value="${login}">
        <label for="inputPassword" class="sr-only">Heslo</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Heslo" required value="${password}">
        <button name="action" class="btn btn-lg btn-success btn-block" value="save" type="submit">Příhlásit se</button>
    </form>

</body>
</html>
