package org.sigei.model;

public class CNPJ {
    private String digitos;

    public CNPJ(String digitos) {
        this.digitos = digitos.replaceAll("\\D", "");
    }

    public String getCnpj() {
        return digitos;
    }

    public String getCnpjFormatado() {
        return digitos.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
