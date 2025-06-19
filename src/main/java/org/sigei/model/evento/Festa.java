package org.sigei.model.evento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Festa extends Evento {
    private final ArrayList<String> atracoes;

    public Festa(String nome, String descricao, LocalEvento local, LocalDateTime data) {
        super(nome, descricao, local, data);
        atracoes = new ArrayList<String>();
    }

    public Festa(String nome, String descricao, LocalEvento local, LocalDateTime data, ArrayList<String> atracoes) {
        super(nome, descricao, local, data);
        this.atracoes = atracoes;
    }

    public Festa(String nome, String descricao, LocalEvento local, LocalDateTime data, String... atracoes) {
        super(nome, descricao, local, data);
        this.atracoes = (ArrayList<String>)Arrays.asList(atracoes);
    }

    public void addAtracao(String nomeAtracao) {
        atracoes.add(nomeAtracao);
    }

    public void addAtracoes(ArrayList<String> atracoes) {
        this.atracoes.addAll(atracoes);
    }

    public void addAtracoes(String... atracoes) {
        addAtracoes((ArrayList<String>)Arrays.asList(atracoes));
    }

    public void rmvAtracao(String nomeAtracao) {
        atracoes.remove(nomeAtracao);
    }
}
