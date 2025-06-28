package org.sigei.model.pessoa;

import org.sigei.exception.ValidationException;
import org.sigei.model.CPF;

public abstract class Pessoa {
    private final CPF cpf;
    private String nome;
    private String sobrenome;

    public Pessoa(CPF cpf, String nome, String sobrenome) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public CPF getCpf() {
        return cpf;
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
