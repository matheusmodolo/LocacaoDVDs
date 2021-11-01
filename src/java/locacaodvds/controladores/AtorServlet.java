package locacaodvds.controladores;

import locacaodvds.dao.AtorDAO;
import locacaodvds.entidades.Ator;
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

@WebServlet(name = "AtorServlet", urlPatterns = {"/processaAtor"})
public class AtorServlet extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        AtorDAO dao = null;
        RequestDispatcher disp = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            dao = new AtorDAO();

            if (acao.equals("inserir")) {

                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("dataEstreia");

                Ator a = new Ator();
                a.setNome(nome);
                a.setSobrenome(sobrenome);
                a.setDataEstreia(Date.valueOf(LocalDate.parse(dataEstreia, dtf)));
                if (validarAtor(a) == true) {
                    dao.salvar(a);

                    disp = request.getRequestDispatcher(
                            "/formularios/Atores/listagem.jsp");
                } else {
                    request.setAttribute("ator", a);
                    disp = request.getRequestDispatcher(
                            "/formularios/ator/erro.jsp");
                }

            } else if (acao.equals("alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("dataEstreia");

                Ator a = new Ator();
                a.setId(id);
                a.setNome(nome);
                a.setSobrenome(sobrenome);
                a.setDataEstreia(Date.valueOf(LocalDate.parse(dataEstreia, dtf)));

                if (validarAtor(a) == true) {
                    dao.atualizar(a);

                    disp = request.getRequestDispatcher(
                            "/formularios/ator/listagem.jsp");
                } else {
                    request.setAttribute("ator", a);
                    disp = request.getRequestDispatcher(
                            "/formularios/Atores/erro.jsp");
                }

            } else if (acao.equals("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Ator a = new Ator();
                a.setId(id);

                dao.excluir(a);

                disp = request.getRequestDispatcher("/formularios/ator/listagem.jsp");

            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                Ator a = dao.obterPorId(id);
                request.setAttribute("ator", a);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/ator/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/ator/excluir.jsp");
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

    public boolean validarAtor(Ator a) {

        boolean v = true;
        String r = a.getNome();
        String s = a.getSobrenome();
        Date d = a.getDataEstreia();

        if (r.length() > 30 || r.length() <= 0) {
            v = false;
        }

        if (s.length() > 30 || s.length() <= 0) {
            v = false;
        }

        if (d == null) {
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
        return "AtorServlet";
    }
}
