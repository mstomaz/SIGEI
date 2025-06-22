package org.sigei.model.builder.pessoa;

import org.sigei.exception.ValidationException;
import org.sigei.model.CPF;
import org.sigei.model.pessoa.Participante;
import org.sigei.validacao.ValidationResult;
import java.time.LocalDate;

public class ParticipanteBuilder extends PessoaValidator {
    public Participante criar(CPF cpf, String nome, String sobrenome, LocalDate dataNasc)
            throws ValidationException {
        ValidationResult result = validar(cpf, nome, sobrenome);

        if (dataNasc.isAfter(LocalDate.now())) {
            result.addErro("DataNasc", "campo.invalido");
        }

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Participante(cpf, nome, sobrenome, dataNasc);
    }
}
