package org.sigei.model.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Festa extends Evento {
    private ArrayList<String> atracoes;

    public Festa(String nome, String descricao, LocalEvento local, LocalDateTime data) {
        super(nome, descricao, local, data);
        tipoEvento = ETipoEvento.FESTA;
        atracoes = new ArrayList<>();
    }

    public Festa(String nome, String descricao, LocalEvento local, LocalDateTime data, ArrayList<String> atracoes) {
        this(nome, descricao, local, data);
        this.atracoes = atracoes;
    }

    public Festa(String nome, String descricao, LocalEvento local, LocalDateTime data, String... atracoes) {
        this(nome, descricao, local, data);
        this.atracoes = new ArrayList<>(Arrays.asList(atracoes));
    }

    public ArrayList<String> getAtracoes() {
        return atracoes;
    }

    public void addAtracao(String nomeAtracao) {
        atracoes.add(nomeAtracao);
    }

    public void addAtracoes(ArrayList<String> atracoes) {
        this.atracoes.addAll(atracoes);
    }

    public void addAtracoes(String... atracoes) {
        addAtracoes(new ArrayList<>(Arrays.asList(atracoes)));
    }

    public void rmvAtracao(String nomeAtracao) {
        atracoes.remove(nomeAtracao);
    }
}
