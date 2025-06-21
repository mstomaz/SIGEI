package org.sigei.model.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;

public class Palestra extends Evento {
    private String nomePalestrante;

    public Palestra(String nome, String descricao, LocalEvento local, LocalDateTime data, String nomePalestrante) {
        super(nome, descricao, local, data);
        tipoEvento = ETipoEvento.PALESTRA;
        this.nomePalestrante = nomePalestrante;
    }

    public String getNomePalestrante() {
        return nomePalestrante;
    }
}
