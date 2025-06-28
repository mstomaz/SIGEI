package org.sigei.dto.pessoa;

import org.sigei.model.CPF;

public abstract class PessoaDTO {
    private final CPF cpf;
    private final String nome;
    private final String sobrenome;

    public PessoaDTO(String cpf, String nome, String sobrenome) {
        this.cpf = new CPF(cpf);
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}
