<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaDVDs?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>
        <title>DVDs Cadastrados</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <h1>DVDs Cadastrados</h1>

        <p>
            <a href="${cp}/formularios/dvds/novo.jsp">
                Novo DVD
            </a>
        </p>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Título</th>
                    <th>Ano de Lançamento</th>
                    <th>Data de Lançamento</th>
                    <th>Duração em min.</th>
                    <th>Gênero</th>
                    <th>Classificação</th>
                    <th>Ator Principal</th>
                    <th>Ator Coadjuvante</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>

                <jsp:useBean
                    id="servicos"
                    scope="page"
                    class="locacaodvds.servicos.DVDsServices"/>

                <c:forEach items="${servicos.todos}" var="dvds">
                    <tr>
                        <td>${dvds.id}</td>
                        <td>${dvds.titulo}</td>
                        <td>${dvds.anoLancamento}</td>
                        <td>${dvds.dataLancamento}</td>
                        <td>${dvds.duracao}</td>
                        <td>${dvds.genero.descricao}</td>
                        <td>${dvds.classificacao.descricao}</td>
                        <td>${dvds.atorPrincipal.nome} ${dvds.atorPrincipal.sobrenome}</td>
                        <td>${dvds.atorCoadjuvante.nome} ${dvds.atorCoadjuvante.sobrenome}</td>
                        <td>
                            <a href="${cp}/${prefixo}Alteracao&id=${dvds.id}">
                                Alterar
                            </a>
                        </td>
                        <td>
                            <a href="${cp}/${prefixo}Exclusao&id=${dvds.id}">
                                Excluir
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>

        <p>
            <a href="${cp}/formularios/dvds/novo.jsp">
                Novo DVD
            </a>
        </p>

        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
    </body>
</html>