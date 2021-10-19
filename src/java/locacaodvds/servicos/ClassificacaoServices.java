package locacaodvds.servicos;

import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.entidades.Classificacao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoServices {

    public List<Classificacao> getTodos() {

        List<Classificacao> lista = new ArrayList<>();
        ClassificacaoDAO dao = null;

        try {
            dao = new ClassificacaoDAO();
            lista = dao.listarTodos();
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

        return lista;
    }
}
