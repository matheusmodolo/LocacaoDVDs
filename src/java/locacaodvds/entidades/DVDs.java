package locacaodvds.entidades;

import java.sql.Date;

/**
 *
 * @author nmmat
 */
public class DVDs {
    
    private int id;
    private String titulo;
    private String anoLancamento;
    private Date dataLancamento;
    private String duracao;
    private Genero genero;
    private Classificacao classificacao;
    private Ator atorPrincipal;
    private Ator atorCoadjuvante;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Ator getAtorPrincipal() {
        return atorPrincipal;
    }

    public void setAtorPrincipal(Ator atorPrincipal) {
        this.atorPrincipal = atorPrincipal;
    }

    public Ator getAtorCoadjuvante() {
        return atorCoadjuvante;
    }

    public void setAtorCoadjuvante(Ator atorCoadjuvante) {
        this.atorCoadjuvante = atorCoadjuvante;
    }   

    @Override
    public String toString() {
        return "DVDs{" + "id=" + id + ", titulo=" + titulo + ", anoLancamento=" + anoLancamento + ", dataLancamento=" + dataLancamento + ", duracao=" + duracao + ", genero=" + genero + ", classificacao=" + classificacao + ", atorPrincipal=" + atorPrincipal + ", atorCoadjuvante=" + atorCoadjuvante + '}';
    }

}
