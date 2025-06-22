package org.sigei.model.usuario;

import org.sigei.exception.TipoUsuarioNaoEncontradoException;
import org.sigei.model.pessoa.Administrador;
import org.sigei.model.pessoa.Organizador;
import org.sigei.model.pessoa.Participante;
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
        switch (dadosPessoais) {
            case Administrador adm -> {
                return ETipoUsuario.ADMINISTRADOR;
            }
            case Participante part -> {
                return ETipoUsuario.PARTICIPANTE;
            }
            case Organizador org -> {
                return ETipoUsuario.ORGANIZADOR;
            }
            default -> {
                throw new TipoUsuarioNaoEncontradoException();
            }
        }
    }
}
