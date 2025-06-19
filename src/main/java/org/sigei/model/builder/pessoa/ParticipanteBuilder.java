package org.sigei.model.builder.pessoa;

import org.sigei.exception.ValidationException;
import org.sigei.model.pessoa.Participante;
import org.sigei.validacao.ValidationResult;

import java.time.LocalDate;

public class ParticipanteBuilder<CPF> extends PessoaValidator<CPF> {
    public Participante<CPF> criar(CPF cpf, String nome, String sobrenome, LocalDate dataNasc)
            throws ValidationException {
        ValidationResult result = validar(cpf, nome, sobrenome, dataNasc);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Participante<>(cpf, nome, sobrenome, dataNasc);
    }
}
