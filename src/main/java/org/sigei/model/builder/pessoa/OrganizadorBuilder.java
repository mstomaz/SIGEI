package org.sigei.model.builder.pessoa;

import org.sigei.exception.ValidationException;
import org.sigei.validacao.ValidationResult;
import org.sigei.model.pessoa.Organizador;

import java.time.LocalDate;

public class OrganizadorBuilder<C> extends PessoaValidator<C> {
    public Organizador<C> criar(C chave, String nome, String sobrenome, LocalDate dataNasc)
            throws ValidationException {
        ValidationResult result = validar(chave, nome, sobrenome, dataNasc);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Organizador<>(chave, nome, sobrenome, dataNasc);
    }
}
