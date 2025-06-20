package org.sigei.model.pessoa;

public class Administrador<C> extends Pessoa<C> {
    public Administrador() {
        super();
    };
    public Administrador(C chave, String nome, String sobrenome) {
        super(chave, nome, sobrenome);
    }
}
