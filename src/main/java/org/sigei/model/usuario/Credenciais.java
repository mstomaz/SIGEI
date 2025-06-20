package org.sigei.model.usuario;

import org.sigei.exception.ValidationException;

public class Credenciais {
    private String login;
    private String senha;

    public Credenciais() {};
    public Credenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() { return login; }

    public void setLogin(String login) throws ValidationException {
        if (login.isBlank()) {
            throw new ValidationException("Login", "campo.obrigatorio");
        }
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws ValidationException {
        if (senha.isBlank()) {
            throw new ValidationException("Senha", "campo.obrigatorio");
        }
        this.senha = senha;
    }
}
