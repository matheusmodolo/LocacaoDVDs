<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Sistema para Cadastro de DVD's</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <h1>Sistema para Cadastro de DVD's</h1>

        <p>
            <a href="${cp}/formularios/dvds/listagem.jsp"><h3>DVD's</h3></a>
        </p>
        <p>
            <a href="${cp}/formularios/ator/listagem.jsp"><h3>Atores</h3></a>
        </p>
        <p>
            <a href="${cp}/formularios/genero/listagem.jsp"><h3>Gêneros</h3></a>
        </p>
        <p>
            <a href="${cp}/formularios/classificacao/listagem.jsp"><h3>Classificações</h3></a>
        </p>

    </body>

</html>