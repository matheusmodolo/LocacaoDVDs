<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Excluir DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <h1>Excluir DVD</h1>

        <form method="post" action="${cp}/processaDVDs">

            <input name="acao" type="hidden" value="excluir"/>
            <input name="id" type="hidden" value="${requestScope.dvds.id}"/>

            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>${requestScope.dvds.titulo}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>${requestScope.dvds.anoLancamento}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Data de Lançamento:</td>
                    <td>
                        <fmt:formatDate
                            pattern="dd/MM/yyyy"
                            value="${requestScope.dvds.dataLancamento}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração:</td>
                    <td>${requestScope.dvds.duracao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>${requestScope.dvds.genero.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificacao:</td>
                    <td>${requestScope.dvds.classificacao.descricao}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Principal:</td>
                    <td>${requestScope.dvds.atorPrincipal.nome} ${requestScope.dvds.atorPrincipal.sobrenome}</td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Coadjuvante:</td>
                    <td>${requestScope.dvds.atorCoadjuvante.nome} ${requestScope.dvds.atorCoadjuvante.sobrenome}</td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/dvds/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Excluir"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>