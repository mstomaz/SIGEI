package org.sigei.model.builder.evento;

import org.sigei.model.evento.LocalEvento;
import org.sigei.validacao.ValidationResult;
import java.time.LocalDateTime;

public abstract class EventoValidator {
    protected ValidationResult validar(String nome, LocalEvento local, LocalDateTime data, int qtdIngressos) {
        ValidationResult result = new ValidationResult();

        if (nome.isBlank()) {
            result.addErro("Nome", "campo.obrigatorio");
        }

        ValidationResult endeResult = new LocalEventoBuilder().validar(
                local.getEndereco(),
                local.getLotacao());
        if (!endeResult.ehValido()) {
            endeResult.getErros().forEach(result::addErro);
        }

        if (data.isBefore(LocalDateTime.now())) {
            result.addErro("Data","campo.invalido");
        }

        if (qtdIngressos > local.getLotacao()) {
            result.addErro("QtdIngressos", "qtdIngressos.ultrapassa");
        }

        return result;
    }
}
