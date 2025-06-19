package org.sigei.model;

public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String uf;
    private String referencia;

    public Endereco() {}
    public Endereco(String rua, String numero, String bairro, String uf) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.uf = uf;
    }
    public Endereco(String rua, String numero, String bairro, String uf, String referencia) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.uf = uf;
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

    public String getUf() {
        return uf;
    }

    public String getReferencia() {
        return referencia;
    }
}