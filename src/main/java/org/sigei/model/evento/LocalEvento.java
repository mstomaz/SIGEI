package org.sigei.model.evento;

import org.sigei.model.Endereco;

public class LocalEvento {
    private Endereco endereco;
    private int lotacao;

    public LocalEvento() {}
    public LocalEvento(Endereco endereco, int lotacao) {
        this.endereco = endereco;
        this.lotacao = lotacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public int getLotacao() {
        return lotacao;
    }
}
