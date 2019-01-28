<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Firma - Přidat zaměstnance</title>

    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
       <%@include file="_menu.jsp" %>
    </div>
    <div class="container">
        <form:form modelAttribute="worker" cssClass="form-horizontal">
            <fieldset>

                <legend>Přidání zaměstnance</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputNameDoc">Jméno zaměstnance:</label>
                    <div class="col-md-4">
                        <form:input path="firstName" id="textinputNameDoc" name="textinputNameDoc" type="text"
                                    placeholder="" class="form-control input-md" required=""/>
                        <form:errors path="firstName" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputSurname">Příjmení zaměstnance:</label>
                    <div class="col-md-4">
                        <form:input path="surName" id="textinputSurname" name="textinputSurname" type="text"
                                    placeholder="" class="form-control input-md" required=""/>
                        <form:errors path="surName" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <c:if test="${edit==false}">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputPassword">Heslo zaměstnance:</label>
                    <div class="col-md-4">
                        <form:input path="password" id="textinputPassword" name="textinputPassword" type="password"
                                    placeholder="" class="form-control input-md" required=""/>
                        <form:errors path="password" cssStyle="color: salmon; font-style: italic;"/>
                        <!-- An element to toggle between password visibility -->
                        <label><input style="margin-right: 5px;" type="checkbox" onclick="myFunction()">Show Password</label>
                    </div>

                </div>
                </c:if>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="selectbasicRole">Role:</label>
                    <div class="col-md-4">
                        <form:select path="role.id" id="selectbasicRole" name="selectbasicRole"
                                     class="form-control" required="">
                            <form:option value="0" label="Zvolte roly" />
                            <form:options items="${roles}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="role" cssStyle="color: salmon; font-style: italic;"/>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="selectbasicWorkplace">Pracoviště:</label>
                    <div class="col-md-4">
                        <form:select path="workplace.id" id="selectbasicWorkplace" name="selectbasicWorkplace"
                                     class="form-control">
                            <form:option value="0" label="Zvolte pracoviště" />
                            <form:options items="${workplaces}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="workplace" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="buttonAdd"></label>
                    <div class="col-md-4">
                        <button id="buttonBack" name="action" class="btn btn-info" value="cancel">Zpět</button>
                        <button type="submit" id="buttonAdd" name="action" class="btn btn-success" value="save">Přidat</button>
                    </div>
                </div>

            </fieldset>
        </form:form>
    </div>



    <script>
        function myFunction() {
            var x = document.getElementById("textinputPassword");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
    </script>
</body>
</html>