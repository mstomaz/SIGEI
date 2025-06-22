package org.sigei.model.builder.usuario;

import org.sigei.exception.ValidationException;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.model.usuario.Credenciais;
import org.sigei.model.usuario.Usuario;
import org.sigei.validacao.ValidationResult;

public class UsuarioBuilder {
    public Usuario criar(Pessoa dadosPessoais, Credenciais credenciais)
            throws ValidationException {

        ValidationResult result = new ValidationResult();

        if (!result.ehValido()) {
            throw new ValidationException(result.getErros());
        }

        return new Usuario(dadosPessoais, credenciais);
    }
}
