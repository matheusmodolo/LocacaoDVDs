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

                if (validarDVD(d) == true) {
                    dao.salvar(d);

                    disp = request.getRequestDispatcher(
                            "/formularios/DVDs/listagem.jsp");
                } else {
                    request.setAttribute("dvd", d);
                    disp = request.getRequestDispatcher(
                            "/formularios/DVDs/erro.jsp");
                }

            } else if (acao.equals("alterar")) {

                String dataLancamento = request.getParameter("dataLancamento");

                Genero g = new Genero();
                g.setId(Integer.parseInt(request.getParameter("idGenero")));
                Classificacao c = new Classificacao();
                c.setId(Integer.parseInt(request.getParameter("idClassificacao")));
                Ator ap = new Ator();
                ap.setId(Integer.parseInt(request.getParameter("idAtorPrincipal")));
                Ator ac = new Ator();
                ac.setId(Integer.parseInt(request.getParameter("idAtorCoadjuvante")));

                DVDs d = new DVDs();
                d.setId(Integer.parseInt(request.getParameter("id")));
                d.setTitulo(request.getParameter("titulo"));
                d.setAnoLancamento(request.getParameter("anoLancamento"));
                d.setDataLancamento(Date.valueOf(
                        LocalDate.parse(dataLancamento, dtf)));
                d.setDuracao(request.getParameter("duracao"));
                d.setGenero(g);
                d.setClassificacao(c);
                d.setAtorPrincipal(ap);
                d.setAtorCoadjuvante(ac);

                //System.out.println(d);
                if (validarDVD(d) == true) {
                    dao.atualizar(d);

                    disp = request.getRequestDispatcher(
                            "/formularios/DVDs/listagem.jsp");
                } else {
                    request.setAttribute("dvd", d);
                    disp = request.getRequestDispatcher(
                            "/formularios/DVDs/erro.jsp");
                }

            } else if (acao.equals("excluir")) {

                DVDs d = new DVDs();
                d.setId(Integer.parseInt(request.getParameter("id")));

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

    public boolean validarDVD(DVDs validaDVD) {

        boolean v = true;
        int id = validaDVD.getId();
        String t = validaDVD.getTitulo();
        int al = Integer.parseInt(validaDVD.getAnoLancamento());
        Date dl = validaDVD.getDataLancamento();
        int d = Integer.parseInt(validaDVD.getDuracao());

        if (id == 0) {
            v = false;
        }
        if (t.length() > 45 || t.length() <= 0) {
            v = false;
        }
        if (al < 1888) {
            v = false;
        }
        if (dl == null) {
            v = false;
        }
        if (d <= 0) {
            v = false;
        }
        return v;
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
