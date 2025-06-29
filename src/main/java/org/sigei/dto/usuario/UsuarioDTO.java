package org.sigei.dto.usuario;

import org.sigei.dto.pessoa.PessoaDTO;
import org.sigei.model.usuario.Credenciais;
import org.sigei.model.usuario.enums.ETipoUsuario;

public class UsuarioDTO {
    private final PessoaDTO dadosPessoais;
    private final Credenciais credenciais;
    private final ETipoUsuario tipoUsuario;

    public UsuarioDTO(PessoaDTO dadosPessoais, Credenciais credenciais, ETipoUsuario tipoUsuario) {
        this.dadosPessoais = dadosPessoais;
        this.credenciais = credenciais;
        this.tipoUsuario = tipoUsuario;
    }

    public PessoaDTO getDadosPessoais() {
        return dadosPessoais;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }

    public ETipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
