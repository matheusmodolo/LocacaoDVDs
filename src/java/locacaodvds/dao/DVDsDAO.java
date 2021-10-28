package locacaodvds.dao;

import locacaodvds.entidades.DVDs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.Classificacao;
import locacaodvds.entidades.Genero;

public class DVDsDAO extends DAO<DVDs> {

    public DVDsDAO() throws SQLException {
    }

    @Override
    public void salvar(DVDs obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO "
                + "dvds ( titulo, "
                + "anoLancamento, "
                + "dataLancamento, "
                + "duracao, "
                + "genero_id, "
                + "classificacao_id, "
                + "atorPrincipal_id, "
                + "atorCoadjuvante_id ) "
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? );");

        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setDate(3, obj.getDataLancamento());
        stmt.setString(4, obj.getDuracao());
        stmt.setInt(5, obj.getGenero().getId());
        stmt.setInt(6, obj.getClassificacao().getId());
        stmt.setInt(7, obj.getAtorPrincipal().getId());
        stmt.setInt(8, obj.getAtorCoadjuvante().getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(DVDs obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "UPDATE dvds "
                + "SET"
                + " titulo = ?,"
                + " anoLancamento = ?,"
                + " dataLancamento = ?,"
                + " duracao = ?,"
                + " genero_id = ?,"
                + " classificacao_id = ?,"
                + " atorPrincipal_id = ?,"
                + " atorCoadjuvante_id = ? "
                + "WHERE"
                + " id = ?;");

        stmt.setString(1, obj.getTitulo());
        stmt.setString(2, obj.getAnoLancamento());
        stmt.setDate(3, obj.getDataLancamento());
        stmt.setString(4, obj.getDuracao());
        stmt.setInt(5, obj.getGenero().getId());
        stmt.setInt(6, obj.getClassificacao().getId());
        stmt.setInt(7, obj.getAtorPrincipal().getId());
        stmt.setInt(8, obj.getAtorCoadjuvante().getId());
        stmt.setInt(9, obj.getId());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public void excluir(DVDs obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM dvds "
                + "WHERE"
                + " id = ?;");

        stmt.setInt(1, obj.getId());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<DVDs> listarTodos() throws SQLException {

        List<DVDs> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT"
                + " d.id idDVD, "
                + " d.titulo tituloDVD, "
                + " d.anoLancamento anoLancamentoDVD, "
                + " d.dataLancamento dataLancamentoDVD, "
                + " d.duracao duracaoDVD, "
                + " g.id idGenero, "
                + " g.descricao descricaoGenero, "
                + " c.id idClassificacao, "
                + " c.descricao descricaoClassificacao, "
                + " ap.id idAtorPrincipal, "
                + " ap.nome nomeAtorPrincipal, "
                + " ap.sobrenome sobrenomeAtorPrincipal, "
                + " ap.dataEstreia dataEstreiaAtorPrincipal, "
                + " ac.id idAtorCoadjuvante, "
                + " ac.nome nomeAtorCoadjuvante, "
                + " ac.sobrenome sobrenomeAtorCoadjuvante, "
                + " ac.dataEstreia dataEstreiaAtorCoadjuvante "
                + "FROM"
                + " dvds d, "
                + " ator ap, "
                + " ator ac, "
                + " genero g, "
                + " classificacao c "
                + "WHERE"
                //+ " d.id = ? AND"
                + " d.atorprincipal_id = ap.id AND"
                + " d.atorcoadjuvante_id = ac.id AND"
                + " d.genero_id = g.id AND"
                + " d.classificacao_id = c.id;");

        //ERRO AQUI
        //stmt.setInt( 1, id );
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            DVDs d = new DVDs();
            Genero g = new Genero();
            Classificacao c = new Classificacao();
            Ator ap = new Ator();
            Ator ac = new Ator();

            d.setId(rs.getInt("idDVD"));
            d.setTitulo(rs.getString("tituloDVD"));
            d.setAnoLancamento(rs.getString("anoLancamentoDVD"));
            d.setDataLancamento(rs.getDate("dataLancamentoDVD"));
            d.setDuracao(rs.getString("duracaoDVD"));

            d.setAtorPrincipal(ap);
            ap.setId(rs.getInt("idAtorPrincipal"));
            ap.setNome(rs.getString("nomeAtorPrincipal"));
            ap.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            ap.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));

            d.setAtorCoadjuvante(ac);
            ac.setId(rs.getInt("idAtorCoadjuvante"));
            ac.setNome(rs.getString("nomeAtorCoadjuvante"));
            ac.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            ac.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));

            d.setGenero(g);
            g.setId(rs.getInt("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));

            d.setClassificacao(c);
            c.setId(rs.getInt("idClassificacao"));
            c.setDescricao(rs.getString("descricaoClassificacao"));

            lista.add(d);

        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public DVDs obterPorId(int id) throws SQLException {

        DVDs d = null;
        Genero g = null;
        Classificacao c = null;
        Ator ap = null;
        Ator ac = null;

        PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM dvds "
                + " d.id idDVD, "
                + " d.titulo tituloDVD, "
                + " d.anoLancamento anoLancamentoDVD, "
                + " d.dataLancamento dataLancamentoDVD, "
                + " d.duracao duracaoDVD, "
                + " g.id idGenero, "
                + " g.descricao descricaoGenero, "
                + " c.id idClassificacao, "
                + " c.descricao descricaoClassificacao, "
                + " ap.id idAtorPrincipal, "
                + " ap.nome nomeAtorPrincipal, "
                + " ap.sobrenome sobrenomeAtorPrincipal, "
                + " ap.dataEstreia dataEstreiaAtorPrincipal, "
                + " ac.id idAtorCoadjuvante, "
                + " ac.nome nomeAtorCoadjuvante, "
                + " ac.sobrenome sobrenomeAtorCoadjuvante, "
                + " ac.dataEstreia dataEstreiaAtorCoadjuvante "
                + "FROM"
                + " dvds d, "
                + " ator ap, "
                + " ator ac, "
                + " genero g, "
                + " classificacao c "
                + "WHERE"
                + " d.atorPrincipal_id = ap.id AND"
                + " d.atorCoadjuvante_id = ac.id AND"
                + " d.genero_id = g.id AND"
                + " d.classificacao_id = c.id "
                + " id = ?"//
                + "ORDER BY d.titulo, d.dataLancamento;");

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            d = new DVDs();
            g = new Genero();
            c = new Classificacao();
            ap = new Ator();
            ac = new Ator();

            d.setId(rs.getInt("idDVD"));
            d.setTitulo(rs.getString("tituloDVD"));
            d.setAnoLancamento(rs.getString("anoLancamentoDVD"));
            d.setDataLancamento(rs.getDate("dataLancamentoDVD"));
            d.setDuracao(rs.getString("duracaoDVD"));

            d.setAtorPrincipal(ap);
            ap.setId(rs.getInt("idAtorPrincipal"));
            ap.setNome(rs.getString("nomeAtorPrincipal"));
            ap.setSobrenome(rs.getString("sobrenomeAtorPrincipal"));
            ap.setDataEstreia(rs.getDate("dataEstreiaAtorPrincipal"));

            d.setAtorCoadjuvante(ac);
            ac.setId(rs.getInt("idAtorCoadjuvante"));
            ac.setNome(rs.getString("nomeAtorCoadjuvante"));
            ac.setSobrenome(rs.getString("sobrenomeAtorCoadjuvante"));
            ac.setDataEstreia(rs.getDate("dataEstreiaAtorCoadjuvante"));

            d.setGenero(g);
            g.setId(rs.getInt("idGenero"));
            g.setDescricao(rs.getString("descricaoGenero"));

            d.setClassificacao(c);
            c.setId(rs.getInt("idClassificacao"));
            c.setDescricao(rs.getString("descricaoClassificacao"));
        }

        rs.close();
        stmt.close();

        return d;
    }
}
