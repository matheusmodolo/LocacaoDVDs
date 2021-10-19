package locacaodvds.controladores;

import locacaodvds.dao.DVDsDAO;
import locacaodvds.entidades.Genero;
import locacaodvds.entidades.Classificacao;
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.DVDs;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DVDsServlet",
        urlPatterns = {"/processaDVDs"})
public class DVDsServlet extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        DVDsDAO dao = null;
        RequestDispatcher disp = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            dao = new DVDsDAO();

            if (acao.equals("inserir")) {

                String titulo = request.getParameter("titulo");
                String anoLancamento = request.getParameter("anoLancamento");
                String dataLancamento = request.getParameter("dataLancamento");
                String duracao = request.getParameter("duracao");

                int idGenero = Integer.parseInt(
                        request.getParameter("idGenero"));
                Genero g = new Genero();
                g.setId(idGenero);

                int idClassificacao = Integer.parseInt(
                        request.getParameter("idClassificacao"));
                Classificacao c = new Classificacao();
                c.setId(idClassificacao);

                int idAtorPrincipal = Integer.parseInt(
                        request.getParameter("idAtorPrincipal"));
                Ator ap = new Ator();
                ap.setId(idAtorPrincipal);

                int idAtorCoadjuvante = Integer.parseInt(
                        request.getParameter("idAtorCoadjuvante"));
                Ator ac = new Ator();
                ac.setId(idAtorCoadjuvante);

                DVDs d = new DVDs();
                d.setTitulo(titulo);
                d.setAnoLancamento(anoLancamento);
                d.setDataLancamento(Date.valueOf(
                        LocalDate.parse(dataLancamento, dtf)));
                d.setDuracao(duracao);
                d.setGenero(g);
                d.setClassificacao(c);
                d.setAtorPrincipal(ap);
                d.setAtorCoadjuvante(ac);

                dao.salvar(d);

                disp = request.getRequestDispatcher(
                        "/formularios/dvds/listagem.jsp");

            } else if (acao.equals("alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String titulo = request.getParameter("titulo");
                String anoLancamento = request.getParameter("anoLancamento");
                String dataLancamento = request.getParameter("dataLancamento");
                String duracao = request.getParameter("duracao");
                int idGenero = Integer.parseInt(
                        request.getParameter("idgenero"));
                int idClassificacao = Integer.parseInt(
                        request.getParameter("idClassificacao"));
                int idAtorPrincipal = Integer.parseInt(
                        request.getParameter("idAtorPrincipal"));
                int idAtorCoadjuvante = Integer.parseInt(
                        request.getParameter("idAtorCoadjuvante"));

                Genero g = new Genero();
                g.setId(idGenero);
                Classificacao c = new Classificacao();
                c.setId(idClassificacao);
                Ator ap = new Ator();
                ap.setId(idAtorPrincipal);
                Ator ac = new Ator();
                ac.setId(idAtorCoadjuvante);

                DVDs d = new DVDs();
                d.setId(id);
                d.setTitulo(titulo);
                d.setAnoLancamento(anoLancamento);
                d.setDataLancamento(Date.valueOf(
                        LocalDate.parse(dataLancamento, dtf)));
                d.setDuracao(duracao);
                d.setGenero(g);
                d.setClassificacao(c);
                d.setAtorPrincipal(ap);
                d.setAtorCoadjuvante(ac);

                dao.atualizar(d);

                disp = request.getRequestDispatcher(
                        "/formularios/dvds/listagem.jsp");

            } else if (acao.equals("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                DVDs d = new DVDs();
                d.setId(id);

                dao.excluir(d);

                disp = request.getRequestDispatcher(
                        "/formularios/dvds/listagem.jsp");

            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                DVDs d = dao.obterPorId(id);
                request.setAttribute("dvds", d);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/dvds/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/dvds/excluir.jsp");
                }
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
        } finally {
            if (dao != null) {
                try {
                    dao.fecharConexao();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        }

        if (disp != null) {
            disp.forward(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DVDsServlet";
    }
}
