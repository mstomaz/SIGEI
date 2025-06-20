package org.sigei.model.pessoa;

import org.sigei.exception.ValidationException;

public class Pessoa<C> {
    private C chave;
    private String nome;
    private String sobrenome;

    public Pessoa() {}
    public Pessoa(C chave, String nome, String sobrenome) {
        this.chave = chave;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public C getChave() {
        return chave;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValidationException {
        if (nome.isBlank()) {
            throw new ValidationException("Nome", "campo.obrigatorio");
        }
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) throws ValidationException {
        if (sobrenome.isBlank()) {
            throw new ValidationException("Sobrenome", "campo.obrigatorio");
        }
        this.sobrenome = sobrenome;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }
}
