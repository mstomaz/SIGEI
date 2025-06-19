package org.sigei.model.evento;

import java.time.LocalDateTime;

public class Feira extends Evento {
    public Feira(String nome, String descricao, LocalEvento local, LocalDateTime data) {
        super(nome, descricao, local, data);
    }
}
