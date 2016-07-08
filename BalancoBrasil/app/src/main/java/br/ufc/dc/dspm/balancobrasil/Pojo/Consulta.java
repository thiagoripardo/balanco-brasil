package br.ufc.dc.dspm.balancobrasil.Pojo;

/**
 * Created by root on 07/07/16.
 */
public class Consulta {
    private String nomeFuncao;
    private String nomePrograma;
    private String nomeAcao;
    private String nomeFavorecido;
    private String fonteFinalidade;
    private String  valorParcela;
    private String  Mes;

    public Consulta(String nomeFuncao, String nomePrograma, String nomeAcao, String nomeFavorecido, String fonteFinalidade, String valorParcela, String mes) {
        this.nomeFuncao = nomeFuncao;
        this.nomePrograma = nomePrograma;
        this.nomeAcao = nomeAcao;
        this.nomeFavorecido = nomeFavorecido;
        this.fonteFinalidade = fonteFinalidade;
        this.valorParcela = valorParcela;
        Mes = mes;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public String getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(String nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public String getNomeFavorecido() {
        return nomeFavorecido;
    }

    public void setNomeFavorecido(String nomeFavorecido) {
        this.nomeFavorecido = nomeFavorecido;
    }

    public String getFonteFinalidade() {
        return fonteFinalidade;
    }

    public void setFonteFinalidade(String fonteFinalidade) {
        this.fonteFinalidade = fonteFinalidade;
    }

    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }
    public String toString() {

        return "(" + "Nome da Função :" + nomeFuncao +" "+ "Nome do Favorecido :" +nomeFavorecido + " "+"\n"
                + "Nome da Fonte de Finalidade :" +fonteFinalidade + " "+ "Valor Parcela:" + valorParcela + " " +"\n"
                + "Mes :" +Mes+")";
    }
}
