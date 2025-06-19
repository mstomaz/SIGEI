package org.sigei.model.builder.evento;

import org.sigei.exception.ValidationException;
import org.sigei.model.evento.LocalEvento;
import org.sigei.validacao.ValidationResult;
import org.sigei.model.evento.Palestra;

import java.time.LocalDateTime;

public class PalestraBuilder extends EventoValidator {
    public Palestra criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos, String nomePalestrante)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (nomePalestrante.isBlank()) {
            result.addErro("Palestrante", "campo.obrigatorio");
        }

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Palestra(nome, descricao, local, data, nomePalestrante);
    }
}
