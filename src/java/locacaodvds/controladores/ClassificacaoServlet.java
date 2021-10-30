package locacaodvds.controladores;

import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.entidades.Classificacao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClassificacaoServlet", urlPatterns = {"/processaClassificacao"})
public class ClassificacaoServlet extends HttpServlet {

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        ClassificacaoDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new ClassificacaoDAO();

            if (acao.equals("inserir")) {

                String descricao = request.getParameter("descricao");

                Classificacao c = new Classificacao();
                c.setDescricao(descricao);

                if (validarClassificacao(c) == true) {
                    dao.salvar(c);

                    disp = request.getRequestDispatcher(
                            "/formularios/Classificacoes/listagem.jsp");
                } else {
                    request.setAttribute("classificacao", c);
                    disp = request.getRequestDispatcher(
                            "/formularios/Classificacoes/erro.jsp");
                }

            } else if (acao.equals("alterar")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String descricao = request.getParameter("descricao");

                Classificacao c = new Classificacao();
                c.setId(id);
                c.setDescricao(descricao);

                if (validarClassificacao(c) == true) {
                    dao.atualizar(c);

                    disp = request.getRequestDispatcher(
                            "/formularios/Classificacoes/listagem.jsp");
                } else {
                    request.setAttribute("classificacao", c);
                    disp = request.getRequestDispatcher(
                            "/formularios/Classificacoes/erro.jsp");
                }

            } else if (acao.equals("excluir")) {

                int id = Integer.parseInt(request.getParameter("id"));

                Classificacao c = new Classificacao();
                c.setId(id);

                dao.excluir(c);

                disp = request.getRequestDispatcher("/formularios/classificacao/listagem.jsp");

            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                Classificacao c = dao.obterPorId(id);
                request.setAttribute("classificacao", c);

                if (acao.equals("prepararAlteracao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/classificacao/alterar.jsp");
                } else if (acao.equals("prepararExclusao")) {
                    disp = request.getRequestDispatcher(
                            "/formularios/classificacao/excluir.jsp");
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

    public boolean validarClassificacao(Classificacao c) {
        boolean v = true;

        String d = c.getDescricao();

        if (d.length() > 30 || d.length() <= 0) {
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
        return "ClassificacaoServlet";
    }
}
