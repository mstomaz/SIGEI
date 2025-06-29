package org.sigei.model.usuario;

import org.sigei.exception.TipoUsuarioNaoEncontradoException;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.model.usuario.enums.ETipoUsuario;

public class Usuario {
    private final Pessoa dadosPessoais;
    private final Credenciais credenciais;
    private final ETipoUsuario tipoUsuario;

    public Usuario(Pessoa dadosPessoais, Credenciais credenciais) {
        this.dadosPessoais = dadosPessoais;
        this.credenciais = credenciais;
        tipoUsuario = defineTipoUsuario();
    }

    public Pessoa getDadosPessoais() {
        return dadosPessoais;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    private ETipoUsuario defineTipoUsuario() {
        return switch (dadosPessoais.getClass().getSimpleName()) {
            case "Administrador" -> ETipoUsuario.ADMINISTRADOR;
            case "Participante" ->  ETipoUsuario.PARTICIPANTE;
            case "Organizador" -> ETipoUsuario.ORGANIZADOR;
            default ->   throw new TipoUsuarioNaoEncontradoException("Tipo de pessoa não encontrado.");
        };

    }
}
