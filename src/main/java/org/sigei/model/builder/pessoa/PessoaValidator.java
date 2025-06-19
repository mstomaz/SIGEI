package org.sigei.model.builder.pessoa;

import org.sigei.validacao.ValidationResult;

import java.time.LocalDate;

public abstract class PessoaValidator<C> {
    protected ValidationResult validar(C chave, String nome, String sobrenome, LocalDate dataNasc) {
        ValidationResult result = new ValidationResult();

        if (nome.isBlank()) {
            result.addErro("Nome", "campo.obrigatorio");
        }

        if (sobrenome.isBlank()) {
            result.addErro("Sobrenome", "campo.obrigatorio");
        }

        if (dataNasc.isAfter(LocalDate.now())) {
            result.addErro("DataNasc", "campo.invalido");
        }

        return result;
    }
}
