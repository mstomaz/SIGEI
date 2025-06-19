package org.sigei.model.pessoa;

import java.time.LocalDate;

public class Administrador<C> extends Pessoa<C> {
    public Administrador() {
        super();
    };
    public Administrador(C chave, String nome, String sobrenome, LocalDate dataNasc) {
        super(chave, nome, sobrenome, dataNasc);
    }
}
