package locacaodvds.servicos;

import locacaodvds.dao.DVDsDAO;
import locacaodvds.entidades.DVDs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DVDsServices {

    public List<DVDs> getTodos() {

        List<DVDs> lista = new ArrayList<>();
        DVDsDAO dao = null;

        try {
            dao = new DVDsDAO();
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
