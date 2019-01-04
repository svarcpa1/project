<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h2>Haly: </h2>
<table>
    <tr><th>Hala</th><th>Id</th></tr>
    <c:forEach items="${haly}" var="hala">
        <tr>
            <td>${hala.name} </td>
            <td>${hala.id}</td>
        </tr>
        <tr>
            <th>Pracoviště</th>
            <th>Id</th>
        </tr>
        <c:forEach items="${hala.workplaces}" var="workplace">
            <tr>
                <td>${workplace.name} </td>
                <td>${workplace.id}</td>
            </tr>
            <tr>
                <td>Dokument</td>
                <td>Id</td>
                <td>datum vytvoření</td>
                <td>datum expirace</td>
            </tr>
            <c:forEach items="${workplace.documents}" var="document">
                <tr>
                    <td>${document.name} </td>
                    <td>${document.id}</td>
                    <td>${document.dateCreated} </td>
                    <td>${document.dateExpired}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </c:forEach>
</table>

</body>
</html>