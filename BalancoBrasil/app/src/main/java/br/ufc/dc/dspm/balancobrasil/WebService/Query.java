package br.ufc.dc.dspm.balancobrasil.WebService;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 07/07/16.
 */
public class Query {
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("caps")
    private String caps;
    @SerializedName("setor")
    private String setor;
    @SerializedName("fonteFinalidade")
    private String fonteFinalidade;
    @SerializedName("tamanhoBusca")
    private int tamanhoBusca;
    @SerializedName("comecarDe")
    private int comecarDe;
    private int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCaps() {
        return caps;
    }

    public void setCaps(String caps) {
        this.caps = caps;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getFonteFinalidade() {
        return fonteFinalidade;
    }

    public void setFonteFinalidade(String fonteFinalidade) {
        this.fonteFinalidade = fonteFinalidade;
    }

    public int getTamanhoBusca() {
        return tamanhoBusca;
    }

    public void setTamanhoBusca(int tamanhoBusca) {
        this.tamanhoBusca = tamanhoBusca;
    }

    public int getComecarDe() {
        return comecarDe;
    }

    public void setComecarDe(int comecarDe) {
        this.comecarDe = comecarDe;
    }
}
