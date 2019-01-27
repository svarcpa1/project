<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a class="p-2 text-dark" href="/addHall">Přidat halu</a>
<a class="p-2 text-dark" href="/addWorkplace">Přidat pracoviště</a>
<a class="p-2 text-dark" href="/addDocument">Přidat dokumentaci</a>
<a class="p-2 text-dark" href="/addWorker">Přidat zaměstnance</a>
<c:if test="${pageContext.request.userPrincipal != null}">
    <a class="btn btn-outline-danger" href="/logout">Odhlásit se</a>
</c:if>
<c:if test="${pageContext.request.userPrincipal == null}">
<a class="btn btn-outline-primary" href="/login">Přihlásit se</a>
</c:if>