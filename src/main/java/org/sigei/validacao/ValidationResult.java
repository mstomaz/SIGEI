package org.sigei.validacao;

import java.util.Map;
import java.util.LinkedHashMap;

public class ValidationResult {
    private final Map<String, String> erros;

    public ValidationResult() {
        erros = new LinkedHashMap<>();
    }

    public boolean ehValido() {
        return erros.isEmpty();
    }

    public Map<String, String> getErros() {
        return erros;
    }

    public void addErro(String campoChave, String codErro) {
        erros.put(campoChave, codErro);
    }
}
