package org.sigei.dto.ingresso;

import org.sigei.dto.evento.EventoDTO;

public class IngressoDTO{
    private final int id;
    private final EventoDTO evento;

    public IngressoDTO(int id, EventoDTO evento) {
        this.id = id;
        this.evento = evento;
    }

    public int getId() { return id; }
    public EventoDTO getEvento() {
        return evento;
    }
}
