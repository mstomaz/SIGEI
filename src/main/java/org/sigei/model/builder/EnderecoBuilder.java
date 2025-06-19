package org.sigei.model.builder;

import org.sigei.exception.ValidationException;
import org.sigei.model.Endereco;
import org.sigei.validacao.ValidationResult;

public class EnderecoBuilder {
    public ValidationResult validar(String rua, String numero, String bairro, String uf) {
        ValidationResult result = new ValidationResult();

        if (rua.isBlank()) {
            result.addErro("Rua", "campo.obrigatorio");
        }

        if (numero.isBlank()) {
            result.addErro("Número", "campo.obrigatorio");
        }

        if (bairro.isBlank()) {
            result.addErro("Bairro", "campo.obrigatorio");
        }

        if (uf.isBlank()) {
            result.addErro("UF", "campo.obrigatorio");
        }

        return result;
    }

    public Endereco criar(String rua, String numero, String bairro, String uf)
            throws ValidationException {
        ValidationResult result = validar(rua, numero, bairro, uf);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Endereco(rua, numero, bairro, uf);
    }

    public Endereco criar(String rua, String numero, String bairro, String uf, String referencia)
            throws ValidationException {
        ValidationResult result = validar(rua, numero, bairro, uf);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Endereco(rua, numero, bairro, uf, referencia);
    }
}