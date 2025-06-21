package org.sigei.model.evento;

import org.sigei.model.evento.enums.ETipoEvento;

import java.time.LocalDateTime;

public class Oficina extends Evento {
    private String tema;

    public Oficina(String nome, String descricao, LocalEvento local, LocalDateTime data, String tema) {
        super(nome, descricao, local, data);
        tipoEvento = ETipoEvento.OFICINA;
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }
}
