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
    <link href="webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="../../../java/cz/uhk/ppro/project/css/pricing.css" rel="stylesheet">

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
        <h5 class="my-0 mr-md-auto font-weight-normal">Firma</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="../addHall">Přidat halu</a>
            <a class="p-2 text-dark" href="../addWorkplace">Přidat pracoviště</a>
            <a class="p-2 text-dark" href="../addDocument">Přidat dokumentaci</a>
            <a class="p-2 text-dark" href="../addWorker">Přidat zaměstnance</a>
        </nav>
        <a class="btn btn-outline-primary" href="#">Přihlásit se</a>
    </div>
    <div class="container">
        <form:form modelAttribute="document" cssClass="form-horizontal">
            <fieldset>

                <!-- Form Name -->
                <legend>Přidání dokumentace</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputNameDoc">Název dokumentace:</label>
                    <div class="col-md-4">
                        <form:input path="name" id="textinputNameDoc" name="textinputNameDoc" type="text" placeholder="" class="form-control input-md" required=""/>

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
                        <form:select path="workerCreated.id" id="selectbasicWoker" name="selectbasicWoker" class="form-control">
                            <form:option value="0" label="Zvolte autora" />
                            <form:options items="${workers}" itemLabel="fullName" itemValue="id" />
                        </form:select>

                    </div>
                </div>

                <!-- Select Basic -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="selectbasicWorkplace">Pracoviště:</label>
                    <div class="col-md-4">

                        <form:select path="workplace.id" id="selectbasicWorkplace" name="selectbasicWorkplace" class="form-control">
                            <form:option value="0" label="Zvolte pracoviště" />
                            <form:options items="${workplaces}" itemLabel="name" itemValue="id" />
                        </form:select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputDateCreated">Datum platnosti od:</label>
                    <div class="col-md-4">
                        <form:input path="dateCreated" type="text" id="datepicker" name="textinputDateCreated" class="form-control input-md" required=""/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputDateExpired">Datum platnosti do:</label>
                    <div class="col-md-4">
                        <form:input path="dateExpired" type="text" id="datepicker2" name="textinputDateExpired" class="form-control input-md" required=""/>
                    </div>
                </div>

                <!-- File Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="filebuttonDoc">Vybrat dokumentaci:</label>
                    <div class="col-md-4">
                        <input id="filebuttonDoc" name="filebuttonDoc" class="input-file" type="file">
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="buttonAdd"></label>
                    <div class="col-md-4">
                        <button id="buttonBack" name="action" class="btn btn-info" value="cancel">Zpět</button>
                        <button type="submit" id="buttonAdd" name="action" class="btn btn-success" value="save">Přidat</button>
                    </div>
                </div>

            </fieldset>
        </form:form>


        <footer class="pt-4 my-md-5 pt-md-5 border-top">
            <div class="row">

                <div class="col-6 col-md">
                    <h5>Features</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Cool stuff</a></li>
                        <li><a class="text-muted" href="#">Random feature</a></li>
                        <li><a class="text-muted" href="#">Team feature</a></li>
                        <li><a class="text-muted" href="#">Stuff for developers</a></li>
                        <li><a class="text-muted" href="#">Another one</a></li>
                        <li><a class="text-muted" href="#">Last time</a></li>
                    </ul>
                </div>
                <div class="col-6 col-md">
                    <h5>Resources</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Resource</a></li>
                        <li><a class="text-muted" href="#">Resource name</a></li>
                        <li><a class="text-muted" href="#">Another resource</a></li>
                        <li><a class="text-muted" href="#">Final resource</a></li>
                    </ul>
                </div>
                <div class="col-6 col-md">
                    <h5>About</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Team</a></li>
                        <li><a class="text-muted" href="#">Locations</a></li>
                        <li><a class="text-muted" href="#">Privacy</a></li>
                        <li><a class="text-muted" href="#">Terms</a></li>
                    </ul>
                </div>
                <div class="col-6 col-md">
                    <h5>About</h5>
                    <ul class="list-unstyled text-small">
                        <li><a class="text-muted" href="#">Team</a></li>
                        <li><a class="text-muted" href="#">Locations</a></li>
                        <li><a class="text-muted" href="#">Privacy</a></li>
                        <li><a class="text-muted" href="#">Terms</a></li>
                    </ul>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>