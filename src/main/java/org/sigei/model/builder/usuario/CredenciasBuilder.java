package org.sigei.model.builder.usuario;

import org.sigei.exception.ValidationException;
import org.sigei.model.usuario.Credenciais;
import org.sigei.validacao.ValidationResult;

public class CredenciasBuilder {
    public Credenciais criar(String login, String senha) throws ValidationException {
        ValidationResult result = new ValidationResult();

        if (login.isBlank()) {
            result.addErro("Login", "campo.obrigatorio");
        }

        if (senha.isBlank()) {
            result.addErro("Senha", "campo.obrigatorio");
        }

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Credenciais(login, senha);
    }
}
