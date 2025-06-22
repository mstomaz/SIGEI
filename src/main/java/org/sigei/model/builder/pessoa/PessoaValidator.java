package org.sigei.model.builder.pessoa;

import org.sigei.model.CPF;
import org.sigei.validacao.ValidationResult;

public abstract class PessoaValidator {
    protected ValidationResult validar(CPF cpf, String nome, String sobrenome) {
        ValidationResult result = new ValidationResult();

        if (nome.isBlank()) {
            result.addErro("Nome", "campo.obrigatorio");
        }

        if (sobrenome.isBlank()) {
            result.addErro("Sobrenome", "campo.obrigatorio");
        }

        return result;
    }
}
