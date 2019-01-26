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

    <!-- Bootstrap core CSS -->
    <link href="webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <link href="../css/pricing.css" rel="stylesheet">
</head>
<body>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal"><a href="./">Firma</a></h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="../addHall">Přidat halu</a>
            <a class="p-2 text-dark" href="../addWorkplace">Přidat pracoviště</a>
            <a class="p-2 text-dark" href="../addDocument">Přidat dokumentaci</a>
            <a class="p-2 text-dark" href="../addWorker">Přidat zaměstnance</a>
        </nav>
        <a class="btn btn-outline-primary" href="#">Přihlásit se</a>
    </div>
    <div class="container">
        <form:form modelAttribute="worker" cssClass="form-horizontal">
            <fieldset>

                <!-- Form Name -->
                <legend>Přidání zaměstnance</legend>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputNameDoc">Jméno zaměstnance:</label>
                    <div class="col-md-4">
                        <form:input path="firstName" id="textinputNameDoc" name="textinputNameDoc" type="text"
                                    placeholder="" class="form-control input-md" required=""/>
                        <form:errors path="firstName" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinputSurname">Příjmení zaměstnance:</label>
                    <div class="col-md-4">
                        <form:input path="surName" id="textinputSurname" name="textinputSurname" type="text"
                                    placeholder="" class="form-control input-md" required=""/>
                        <form:errors path="surName" cssStyle="color: salmon; font-style: italic;"/>
                    </div>
                </div>

                <!-- Textarea -->
<%--
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textareaDco">Pracovní pozice zaměstnance:</label>
                    <div class="col-md-4">
                        <form:textarea path="role" class="form-control" id="textareaDco" name="textareaDco"/>
                    </div>
                </div>


--%>
                <!-- Select Basic -->
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

                <!-- Select Basic -->
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