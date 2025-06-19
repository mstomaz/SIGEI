package org.sigei.model.builder.evento;

import org.sigei.exception.ValidationException;
import org.sigei.model.evento.LocalEvento;
import org.sigei.model.evento.Feira;
import org.sigei.validacao.ValidationResult;

import java.time.LocalDateTime;

public class FeiraBuilder extends EventoValidator {
    public Feira criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Feira(nome, descricao, local, data);
    }
}
