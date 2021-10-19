<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Novo DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <h1>Novo Cliente</h1>

        <form method="post" action="${cp}/processaDVDs">

            <input name="acao" type="hidden" value="inserir"/>

            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="20"
                               maxlength="45"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>
                        <input name="anoLancamento"
                               type="text"
                               size="4"
                               maxlength="5"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Data de Lançamento:</td>
                    <td>
                        <input name="dataLancamento"
                               type="date"
                               size="8"
                               placeholder="dd/mm/yyyy"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração em minutos: </td>
                    <td>
                        <input name="duracao"
                               type="text"
                               size="4"
                               required/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>
                        <jsp:useBean
                            id="generoservicos"
                            scope="page"
                            class="locacaodvds.servicos.GeneroServices"/>

                        <select name="idGenero" required>
                            <c:forEach items="${generoservicos.todos}" var="genero">
                                <option value="${genero.id}">
                                    ${genero.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação:</td>
                    <td>
                        <jsp:useBean
                            id="classificacaoservicos"
                            scope="page"
                            class="locacaodvds.servicos.ClassificacaoServices"/>

                        <select name="idClassificacao" required>
                            <c:forEach items="${classificacaoservicos.todos}" var="classificacao">
                                <option value="${classificacao.id}">
                                    ${classificacao.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Principal:</td>
                    <td>
                        <jsp:useBean
                            id="atorprincipalservicos"
                            scope="page"
                            class="locacaodvds.servicos.AtorServices"/>

                        <select name="idAtorPrincipal" required>
                            <c:forEach items="${atorprincipalservicos.todos}" var="atorPrincipal">
                                <option value="${atorPrincipal.id}">
                                    ${atorPrincipal.nome} ${atorPrincipal.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Coadjuvante: </td>
                    <td>
                        <jsp:useBean
                            id="atorcoadjuvanteservicos"
                            scope="page"
                            class="locacaodvds.servicos.AtorServices"/>

                        <select name="idAtorCoadjuvante" required>
                            <c:forEach items="${atorcoadjuvanteservicos.todos}" var="atorCoadjuvante">
                                <option value="${atorCoadjuvante.id}">
                                    ${atorCoadjuvante.nome} ${atorCoadjuvante.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>                               
                <tr>
                    <td>
                        <a href="${cp}/formularios/dvds/listagem.jsp">
                            Voltar
                        </a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Salvar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>