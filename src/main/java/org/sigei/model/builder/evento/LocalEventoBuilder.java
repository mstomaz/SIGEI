package org.sigei.model.builder.evento;

import org.sigei.exception.ValidationException;
import org.sigei.model.Endereco;
import org.sigei.model.builder.EnderecoBuilder;
import org.sigei.model.evento.LocalEvento;
import org.sigei.validacao.ValidationResult;

public class LocalEventoBuilder {
    public ValidationResult validar(Endereco endereco, int lotacao) {
        ValidationResult result = new ValidationResult();

        ValidationResult endeResult = new EnderecoBuilder().validar(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getUf());

        if (!endeResult.ehValido()) {
            endeResult.getErros().forEach(result::addErro);
        }

        if (lotacao < 0) {
            result.addErro("Lotação", "campo.negativo");
        }

        return result;
    }
    public LocalEvento criar(Endereco endereco, int lotacao) throws ValidationException {
        ValidationResult result = validar(endereco, lotacao);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new LocalEvento(endereco, lotacao);
    }
}
