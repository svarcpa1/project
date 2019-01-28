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
    <title>Firma - Přidat dokumentaci</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="src/main/java/cz/uhk/ppro/project/css/pricing.css" rel="stylesheet">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">

    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
            $( "#datepicker2" ).datepicker();
        } );
    </script>


</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <%@include file="_menu.jsp" %>
    </div>
    <div class="container">
        <form:form modelAttribute="document" cssClass="form-horizontal" enctype="multipart/form-data" method="post">
            <fieldset>

                <!-- Form Name -->
                <legend>Přidání dokumentace</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputNameDoc">Název dokumentace:</label>
                    <div class="col-md-4">
                        <form:input path="name" id="textinputNameDoc" name="textinputNameDoc" type="text"
                                    placeholder="" class="form-control input-md" required=""/>
                        <form:errors path="name" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <!-- Textarea -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textareaDco">Popis dokumentace:</label>
                    <div class="col-md-4">
                        <form:textarea path="description" class="form-control" id="textareaDco" name="textareaDco"/>
                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="selectbasicWoker">Vytvořil:</label>
                    <div class="col-md-4">
                        <form:select path="workerCreated.id" id="selectbasicWoker" name="selectbasicWoker"
                                     class="form-control" required="">
                            <form:option value="0" label="Zvolte autora" />
                            <form:options items="${workers}" itemLabel="fullName" itemValue="id" />
                        </form:select>
                        <form:errors path="workerCreated" cssStyle="color: salmon; font-style: italic;"/>

                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="selectbasicWorkplace">Pracoviště:</label>
                    <div class="col-md-4">

                        <form:select path="workplace.id" id="selectbasicWorkplace" name="selectbasicWorkplace"
                                     class="form-control" required="" >
                            <form:option value="0" label="Zvolte pracoviště" />
                            <form:options items="${workplaces}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="workplace" cssStyle="color: salmon; font-style: italic;"/>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputDateCreated">Datum platnosti od:</label>
                    <div class="col-md-4">
                        <form:input path="dateCreated" autocomplete="off" type="text" id="datepicker" name="textinputDateCreated"
                                    class="form-control input-md" required=""/>
                        <form:errors path="dateCreated" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputDateExpired">Datum platnosti do:</label>
                    <div class="col-md-4">
                        <form:input path="dateExpired" autocomplete="off" type="text" id="datepicker2" name="textinputDateExpired"
                                    class="form-control input-md" required=""/>
                        <form:errors path="dateExpired" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <!-- File Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="filebuttonDoc">Vybrat dokumentaci:</label>
                    <div class="col-md-4">
                        <input id="filebuttonDoc" name="file" class="input-file" type="file" accept="application/pdf" />

                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="buttonAdd"></label>
                    <div class="col-md-4">
                        <button id="buttonBack" name="action" class="btn btn-info" value="cancel">Zpět</button>
                        <button type="submit" id="buttonAdd" name="action" class="btn btn-success"
                                value="save">Přidat</button>
                    </div>
                </div>

            </fieldset>
        </form:form>
    </div>
</body>
</html>