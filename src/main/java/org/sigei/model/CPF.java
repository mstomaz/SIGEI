package org.sigei.model;

public class CPF {
    private String digitos;
    
    public CPF(String digitos) {
        this.digitos = digitos.replaceAll("\\D", "");
    }
    
    public String getCpf() {
        return digitos;
    }

    public String getCpfFormatado() {
        return digitos.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
