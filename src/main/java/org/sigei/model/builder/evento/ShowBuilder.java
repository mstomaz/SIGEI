package org.sigei.model.builder.evento;

import org.sigei.exception.ValidationException;
import org.sigei.model.evento.Show;
import org.sigei.model.evento.LocalEvento;
import org.sigei.validacao.ValidationResult;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShowBuilder extends EventoValidator {
    public Show criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos, ArrayList<String> lineUp)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Show(nome, descricao, local, data, lineUp);
    }

    public Show criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Show(nome, descricao, local, data);
    }

    public Show criar(String nome, String descricao, LocalEvento local, LocalDateTime data, int qtdIngressos, String... lineUp)
            throws ValidationException {
        ValidationResult result = validar(nome, local, data, qtdIngressos);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Show(nome, descricao, local, data, lineUp);
    }
}
