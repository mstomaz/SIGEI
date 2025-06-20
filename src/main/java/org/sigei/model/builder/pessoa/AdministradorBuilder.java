package org.sigei.model.builder.pessoa;

import org.sigei.exception.ValidationException;
import org.sigei.validacao.ValidationResult;
import org.sigei.model.pessoa.Administrador;

public class AdministradorBuilder<C> extends PessoaValidator<C> {
    public Administrador<C> criar(C chave, String nome, String sobrenome)
            throws ValidationException {
        ValidationResult result = validar(chave, nome, sobrenome);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Administrador<>(chave, nome, sobrenome);
    }
}
