package org.sigei.model.builder.pessoa;

import org.sigei.exception.ValidationException;
import org.sigei.model.CPF;
import org.sigei.validacao.ValidationResult;
import org.sigei.model.pessoa.Organizador;

public class OrganizadorBuilder extends PessoaValidator {
    public Organizador criar(CPF cpf, String nome, String sobrenome)
            throws ValidationException {
        ValidationResult result = validar(cpf, nome, sobrenome);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Organizador(cpf, nome, sobrenome);
    }
}
