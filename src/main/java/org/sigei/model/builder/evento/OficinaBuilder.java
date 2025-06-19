package org.sigei.model.builder.evento;

import org.sigei.exception.ValidationException;
import org.sigei.model.evento.LocalEvento;
import org.sigei.model.evento.Oficina;
import org.sigei.validacao.ValidationResult;

import java.time.LocalDateTime;

public class OficinaBuilder extends EventoValidator {
    public Oficina criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos, String tema)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (tema.isBlank()) {
            result.addErro("Tema", "campo.obrigatorio");
        }

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Oficina(nome, descricao, local, data, tema);
    }
}
