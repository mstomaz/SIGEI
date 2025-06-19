package org.sigei.model.evento;

import java.time.LocalDateTime;

public class Oficina extends Evento {
    private String tema;

    public Oficina(String nome, String descricao, LocalEvento local, LocalDateTime data, String tema) {
        super(nome, descricao, local, data);
        this.tema = tema;
    }
}
