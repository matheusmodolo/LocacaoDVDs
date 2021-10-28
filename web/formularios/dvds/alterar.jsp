<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Alterar DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>

    <body>

        <h1>Alterar DVD</h1>

        <form method="post" action="${cp}/processaDVDs">

            <input name="acao" type="hidden" value="alterar"/>
            <input name="id" type="hidden" value="${requestScope.dvds.id}"/>

            <table>
                <tr>
                    <td class="alinharDireita">Titulo:</td>
                    <td>
                        <input name="titulo"
                               type="text"
                               size="20"
                               maxlength="45"
                               required
                               value="${requestScope.dvds.titulo}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de Lançamento:</td>
                    <td>
                        <input name="anoLancamento"
                               type="text"
                               size="4"
                               maxlength="5"
                               required
                               value="${requestScope.dvds.anoLancamento}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Data de Lançamento:</td>
                    <td>
                        <fmt:formatDate
                            pattern="yyyy-MM-dd"
                            value="${requestScope.dvds.dataLancamento}"
                            var="data" scope="page"/>
                        <input name="dataLancamento"
                               type="date"
                               size="8"
                               placeholder="dd/mm/yyyy"
                               required
                               value="${data}"/>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração:</td>
                    <td>
                        <input name="duracao"
                               type="text"
                               size="13"
                               required
                               value="${requestScope.dvds.duracao}"/>
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
                                <c:choose>
                                    <c:when test="${requestScope.dvds.genero.id eq genero.id}">
                                        <option value="${genero.id}" selected>
                                            ${genero.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${genero.id}">
                                            ${genero.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
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
                                <c:choose>
                                    <c:when test="${requestScope.dvds.classificacao.id eq classificacao.id}">
                                        <option value="${classificacao.id}" selected>
                                            ${classificacao.descricao}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${classificacao.id}">
                                            ${classificacao.descricao}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
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
                                <c:choose>
                                    <c:when test="${requestScope.dvds.atorPrincipal.id eq atorPrincipal.id}">
                                        <option value="${atorPrincipal.id}" selected>
                                            ${atorPrincipal.nome} ${atorPrincipal.sobrenome}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${atorPrincipal.id}">
                                            ${atorPrincipal.nome} ${atorPrincipal.sobrenome}
                                        </option> 
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator Coadjuvante:</td>
                    <td>

                        <jsp:useBean
                            id="atorcoadjuvanteservicos"
                            scope="page"
                            class="locacaodvds.servicos.AtorServices"/>

                        <select name="idAtorCoadjuvante" required>
                            <c:forEach items="${atorcoadjuvanteservicos.todos}" var="atorCoadjuvante">
                                <c:choose>
                                    <c:when test="${requestScope.dvds.atorCoadjuvante.id eq atorCoadjuvante.id}">
                                        <option value="${atorCoadjuvante.id}" selected>
                                            ${atorCoadjuvante.nome} ${atorCoadjuvante.sobrenome}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${atorCoadjuvante.id}">
                                            ${atorCoadjuvante.nome} ${atorCoadjuvante.sobrenome}
                                        </option> 
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="${cp}/formularios/dvds/listagem.jsp">Voltar</a>
                    </td>
                    <td class="alinharDireita">
                        <input type="submit" value="Alterar"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>