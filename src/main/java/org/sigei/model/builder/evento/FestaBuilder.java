package org.sigei.model.builder.evento;

import org.sigei.exception.ValidationException;
import org.sigei.model.evento.LocalEvento;
import org.sigei.model.evento.Festa;
import org.sigei.validacao.ValidationResult;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FestaBuilder extends EventoValidator {
    public Festa criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos, ArrayList<String> atracoes)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Festa(nome, descricao, local, data, atracoes);
    }

    public Festa criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Festa(nome, descricao, local, data);
    }

    public Festa criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos, String... atracoes)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Festa(nome, descricao, local, data, atracoes);
    }
}
