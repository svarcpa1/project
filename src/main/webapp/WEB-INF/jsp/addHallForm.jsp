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
    <title>Firma - Přidat halu</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/">Firma</a></h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <%@include file="_menu.jsp" %>
        </nav>
    </div>
    <div class="container">
        <form:form modelAttribute="hall" cssClass="form-horizontal">
            <fieldset>

                <legend>Přídání nové haly</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textInputName">Název haly:</label>
                    <div class="col-md-4">
                        <form:input path="name" id="textInputName" name="textInputName" type="text"
                                    placeholder="Zadejte název haly " class="form-control input-md" required=""/>
                        <form:errors path="name" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textareaDescription">Popis haly</label>
                    <div class="col-md-4">
                        <form:textarea path="description" class="form-control" id="textareaDescription"
                                       name="textareaDescription"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="buttonAdd"></label>
                    <div class="col-md-4">
                        <button id="buttonBack" name="action" class="btn btn-info" value="cancel">Zpět</button>
                        <button type="submit" id="buttonAdd" name="action" class="btn btn-success" value="save">
                            Přidat</button>
                    </div>
                </div>

            </fieldset>
        </form:form>
    </div>
</body>
</html>