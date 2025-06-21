package org.sigei.model;

public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String referencia;

    public Endereco() {}
    public Endereco(String rua, String numero, String bairro, String cidade, String uf) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
    public Endereco(String rua, String numero, String bairro, String uf, String cidade, String referencia) {
        this(rua, numero, bairro, uf, cidade);
        this.referencia = referencia;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getReferencia() {
        return referencia;
    }
}