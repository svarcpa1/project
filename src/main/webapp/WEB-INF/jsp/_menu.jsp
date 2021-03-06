<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<h5 class="my-0 mr-md-auto font-weight-normal"><a href="/">Firma</a></h5>

<nav class="my-2 my-md-0 mr-md-3">

    <a class="p-2 text-dark" href="/">Domů</a>
    <security:authorize access="hasRole('ROLE_Admin')">
        <a class="p-2 text-dark" href="/addHall">Přidat halu</a>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_Admin')">
        <a class="p-2 text-dark" href="/addWorkplace">Přidat pracoviště</a>
    </security:authorize>

    <security:authorize access="hasAnyRole('ROLE_Admin', 'ROLE_Master')">
        <a class="p-2 text-dark" href="/addDocument">Přidat dokumentaci</a>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_Admin')">
        <a class="p-2 text-dark" href="/addWorker">Přidat zaměstnance</a>
    </security:authorize>
    <c:if test="${pageContext.request.userPrincipal != null}">
        <span class="p-2 text-primary">${pageContext.request.userPrincipal.name}</span>

        <a class="btn btn-outline-danger" href="/logout">Odhlásit se</a>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal == null}">
        <a class="btn btn-outline-primary" href="/login">Přihlásit se</a>
    </c:if>

</nav>