package org.sigei.model.usuario;

import org.sigei.model.pessoa.Administrador;
import org.sigei.model.pessoa.Organizador;
import org.sigei.model.pessoa.Participante;
import org.sigei.model.pessoa.Pessoa;
import org.sigei.model.usuario.enums.ETipoUsuario;

public class Usuario<C> {
    private final Pessoa<C> dadosPessoais;
    private final Credenciais credenciais;
    private final ETipoUsuario tipoUsuario;

    public Usuario(Pessoa<C> dadosPessoais, Credenciais credenciais) {
        this.dadosPessoais = dadosPessoais;
        this.credenciais = credenciais;
        tipoUsuario = defineTipoUsuario();
    }

    public Pessoa<C> getDadosPessoais() {
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
            case Administrador<C> adm -> {
                return ETipoUsuario.ADMINISTRADOR;
            }
            case Participante<C> part -> {
                return ETipoUsuario.PARTICIPANTE;
            }
            case Organizador<C> org -> {
                return ETipoUsuario.ORGANIZADOR;
            }
            default -> {
                //TODO
                throw new RuntimeException("Tipo não encontrado");
            }
        }
    }
}
