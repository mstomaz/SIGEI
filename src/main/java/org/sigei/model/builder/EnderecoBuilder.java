package org.sigei.model.builder;

import org.sigei.exception.ValidationException;
import org.sigei.model.Endereco;
import org.sigei.validacao.ValidationResult;

public class EnderecoBuilder {
    public ValidationResult validar(String rua, String numero, String bairro, String cidade, String uf) {
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

        if (cidade.isBlank()) {
            result.addErro("Cidade", "campo.obrigatorio");
        }

        if (uf.isBlank()) {
            result.addErro("UF", "campo.obrigatorio");
        }

        if (uf.length() > 2) {
            result.addErro("UF", "campo.invalido");
        }

        return result;
    }

    public Endereco criar(String rua, String numero, String bairro, String cidade, String uf)
            throws ValidationException {
        ValidationResult result = validar(rua, numero, bairro, cidade, uf);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Endereco(rua, numero, bairro, cidade, uf);
    }

    public Endereco criar(String rua, String numero, String bairro, String uf, String cidade, String referencia)
            throws ValidationException {
        ValidationResult result = validar(rua, numero, bairro, cidade, uf);

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Endereco(rua, numero, bairro, uf, cidade, referencia);
    }
}