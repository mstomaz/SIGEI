package org.sigei.controller;

import org.sigei.dao.usuario.UsuarioDAO;
import org.sigei.dto.usuario.UsuarioDTO;

public class LoginController {
    public UsuarioDTO Login(String login, String senha)
            throws Exception {
        if (login.isBlank() || senha.isBlank()) {
            throw new IllegalArgumentException("Login e/ou senha inválidos.");
        }

        UsuarioDTO u = new UsuarioDAO().buscarPorLoginESenha(login, senha);
        if (u == null) {
            throw new NullPointerException("Usuário não encontrado.");
        }

        return u;
    }
}
